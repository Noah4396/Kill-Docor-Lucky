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
    this.rooms = model == null ? null : model.getRooms();
    this.target = model == null ? null : new Player(model.getTarget());
    this.amplificationFactor = 0;
    this.players = new ArrayList<>();
    this.requestFocus();
  }

  public void setZOrder(Component component, int index) {
    if (this.isAncestorOf(component)) {
      this.setComponentZOrder(component, index);
    } else {
      // Handle the case when the component is not a child of this panel
      // You might need to add the component to the panel before setting its Z-order
    }
  }
  @Override
  protected void paintComponent(Graphics g) {
    if(model == null) {
      return;
    }
    super.paintComponent(g);
    paintRooms(g);
    //paintTarget(g);
    target.paintComponent(g);
    for(Player player : players) {
      player.paintComponent(g);
      setZOrder(player, 0);
    }
  }

  private void paintRooms(Graphics g) {
    int cellWidth = getWidth();
    int cellHeight = getHeight();
    int maxWidth = 0;
    int maxHeight = 0;
    for (Room room : rooms) {
      maxHeight = Math.max(maxHeight, room.getLowerCorner());
      maxWidth = Math.max(maxWidth, room.getRightCorner());
    }
    amplificationFactor = Math.min(cellWidth / (maxWidth + 1), cellHeight / (maxHeight + 1));
    for (Room room : rooms) {
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
      g.drawString("index: " + String.valueOf(room.getIndex()), leftBound + width / 4, upperBound + height / 2 + textHeight / 2);
    }
  }

  public void addPlayer(PlayerCharacter player) {
    players.add(new Player(player));
  }

  class Player extends JComponent {
    private Character character;

    public Player(Character character) {
      this.character = character;
      addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          handleMouseClick(e);
        }
      });
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Room room = character.getRoom();

      int upperBound = room.getUpperCorner();
      int leftBound = room.getLeftCorner();
      // Calculate the width and height of the rectangle
      leftBound *= amplificationFactor;
      upperBound *= amplificationFactor;
      int radius = amplificationFactor;

      int index = room.getCharacterIndex(character);
      if(model.getTurn() == character) {
        g.setColor(Color.RED);
      } else {
        g.setColor(Color.BLUE);
      }
      // Assuming radius is the diameter of the oval
      int centerX = leftBound + radius / 2;
      int centerY = upperBound + radius / 2;

      // Draw the oval
      if(character instanceof TargetCharacter){
        g.setColor(Color.RED);
        g.fillOval(leftBound + index * radius, upperBound, radius, radius);
        return;
      }
      else {
        g.drawOval(leftBound + index * radius, upperBound, radius, radius);
      }

      // Draw the text in the center
      String text = "" + character.getIndex();
      FontMetrics fontMetrics = g.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(text);
      int textHeight = fontMetrics.getHeight();
      int textX = centerX - textWidth / 2;
      textX += index * radius;
      int textY = centerY + textHeight / 2;

      g.drawString(text, textX, textY);
    }

    public String getPlayerName() {
      return character.getName();
    }

    private void handleMouseClick(MouseEvent e) {
      // Handle the mouse click on the player text
      System.out.println("Player text clicked: " + getPlayerName());
    }
  }
}
