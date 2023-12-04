package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import model.ReadOnlyModel;
import world.Character;
import world.PlayerCharacter;
import world.Room;
import world.TargetCharacter;

/**
 * Panel for the game board.
 */
public class GameBoardPanel extends JPanel {
  private final ReadOnlyModel model;
  private final ArrayList<RoomComponent> roomComponents;
  private final ArrayList<Room> rooms;
  private int amplificationFactor;
  private final ArrayList<Player> players;
  private final Player target;

  /**
   * Constructor.
   *
   * @param model the model to use
   */
  public GameBoardPanel(ReadOnlyModel model) {
    this.model = model;
    this.roomComponents = new ArrayList<>();
    this.rooms = model == null ? null : model.getRooms();
    if (model != null) {
      for (Room room : rooms) {
        RoomComponent roomComponent = new RoomComponent(room);
        roomComponents.add(roomComponent);
      }
    }
    this.target = model == null ? null : new Player(model.getTarget());
    this.players = new ArrayList<>();
    this.requestFocus();

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        handleMouseClick(e);
      }
    });
  }

  private void initializeAmp() {
    if (model == null) {
      amplificationFactor = 0;
      return;
    }
    int cellWidth = getWidth();
    int cellHeight = getHeight();
    int maxWidth = 0;
    int maxHeight = 0;
    for (Room room : rooms) {
      maxHeight = Math.max(maxHeight, room.getLowerCorner());
      maxWidth = Math.max(maxWidth, room.getRightCorner());
    }
    amplificationFactor = Math.min(cellWidth / (maxWidth + 1), cellHeight / (maxHeight + 1));
  }

  private void handleMouseClick(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    StringBuffer sb = new StringBuffer();
    // Handle the mouse click on the room
    if (isInRange(x, y, target.x, target.y, target.radius)) {
      sb.append("Target clicked: " + target.getPlayerName() + "\n");
      JOptionPane.showMessageDialog(null, sb.toString());
      return;
    }
    for (Player player : players) {
      if (isInRange(x, y, player.x, player.y, player.radius)) {
        System.out.println("Player clicked: " + player.getPlayerName());
        return;
      }
    }
    for (RoomComponent roomComponent : roomComponents) {
      if (isInRange(x, y, roomComponent.x, roomComponent.y, amplificationFactor)) {
        System.out.println("Room clicked: " + roomComponent.room.getName());
        return;
      }
    }
  }

  private boolean isInRange(int x, int y, int centerX, int centerY, int radius) {
    return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (model == null) {
      return;
    }
    super.paintComponent(g);
    initializeAmp();
    for (RoomComponent roomComponent : roomComponents) {
      roomComponent.paintComponent(g);
    }
    target.paintComponent(g);
    for (Player player : players) {
      player.paintComponent(g);
    }
  }

  public void addPlayer(PlayerCharacter player) {
    try {
      Player newPlayer = new Player(player);
      players.add(newPlayer);
      this.repaint();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public class RoomComponent extends JComponent {
    private Room room;
    public int x;
    public int y;

    public RoomComponent(Room room) {
      this.room = room;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      int upperBound = room.getUpperCorner();
      int leftBound = room.getLeftCorner();
      int lowerBound = room.getLowerCorner();
      int rightBound = room.getRightCorner();

      // Calculate the width and height of the rectangle
      int width = (rightBound - leftBound + 1) * amplificationFactor;
      int height = (lowerBound - upperBound + 1) * amplificationFactor;
      leftBound *= amplificationFactor;
      upperBound *= amplificationFactor;
      // Set the color to a shade of green
      g.setColor(new Color(0, 255, 0, 100));
      g.fillRect(leftBound, upperBound, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(leftBound, upperBound, width, height);
      x = leftBound + width / 2;
      y = upperBound + height / 2;

      // Draw the name of the room in the center
      String roomName = room.getName();
      double stringSize = 0.5;
      int finalSize = (int) (stringSize * amplificationFactor);
      g.setFont(new Font("TimesRoman", Font.PLAIN, finalSize));
      g.setColor(Color.BLACK);  // Set the color to black (adjust as needed)
      FontMetrics fontMetrics = g.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(roomName);
      int textHeight = fontMetrics.getHeight();
      g.drawString(roomName, leftBound + width / 4, upperBound + height / 2 - textHeight / 2);
      // Draw the second line (index)
      g.drawString("index: " + String.valueOf(room.getIndex()), leftBound + width / 4,
          upperBound + height / 2 + textHeight / 2);
      g.setColor(Color.GRAY);
      g.drawOval(x - amplificationFactor, y - amplificationFactor, amplificationFactor * 2,
          amplificationFactor * 2);
    }

  }

  class Player extends JComponent {
    private Character character;
    public int x;
    public int y;
    public int radius;

    public Player(Character character) {
      this.character = character;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Room room = character.getRoom();

      int upperBound = room.getUpperCorner();
      int leftBound = room.getLeftCorner();
      int rightBound = room.getRightCorner();
      // Calculate the width and height of the rectangle
      leftBound *= amplificationFactor;
      upperBound *= amplificationFactor;
      rightBound *= amplificationFactor;
      int width = rightBound - leftBound;
      int radius = amplificationFactor;

      int index = room.getCharacterIndex(character);
      if (model.getTurn() == character) {
        g.setColor(Color.YELLOW);
      } else {
        g.setColor(Color.CYAN);
      }
      // Assuming radius is the diameter of the oval
      int centerX = leftBound + radius / 2;
      int centerY = upperBound + radius / 2;
      int offset = 0;
      x = centerX + index * radius;
      if(x > rightBound) {
        offset = (x - leftBound) / width;
        x = x - offset * width;
      }
      y = centerY + offset * radius;
      this.radius = radius / 2;

      // Draw the oval
      if (character instanceof TargetCharacter) {
        g.setColor(Color.RED);
        g.fillOval(x - this.radius , y - this.radius, radius, radius);
        return;
      } else {
        g.fillOval(x - this.radius , y - this.radius, radius, radius);
      }

      // Draw the text in the center
      String text = "" + character.getIndex();
      g.setColor(Color.black);
      FontMetrics fontMetrics = g.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(text);
      int textHeight = fontMetrics.getHeight();
      int textX = x - textWidth / 2;
      int textY = y + textHeight / 2;

      g.drawString(text, textX, textY);
    }

    public String getPlayerName() {
      return character.getName();
    }

  }
}
