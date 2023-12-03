package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import model.ReadOnlyModel;
import world.Character;
import world.PlayerCharacter;
import world.Room;

/**
 * Panel for the game board.
 */
public class GameBoardPanel extends JPanel {
  private final ReadOnlyModel model;
  private final ArrayList<Room> rooms;
  private int amplificationFactor;
  private final ArrayList<Player> players;

  /**
   * Constructor.
   *
   * @param model the model to use
   */
  public GameBoardPanel(ReadOnlyModel model) {
    this.model = model;
    this.rooms = model == null ? null : model.getRooms();
    this.amplificationFactor = 0;
    this.players = new ArrayList<>();
  }

  @Override
  protected void paintComponent(Graphics g) {
    if(model == null) {
      return;
    }
    super.paintComponent(g);
    paintRooms(g);
    paintTarget(g);
    for(Player player : players) {
      player.paintComponent(g);
    }
  }

  private void paintTarget(Graphics g) {
    Character target = model.getTarget();
    Room room = target.getRoom();

    int upperBound = room.getUpperCorner();
    int leftBound = room.getLeftCorner();
    int lowerBound = room.getLowerCorner();
    int rightBound = room.getRightCorner();

    // Calculate the width and height of the rectangle
    int width = (rightBound - leftBound + 1) * amplificationFactor;
    int height = (lowerBound - upperBound + 1) * amplificationFactor;
    leftBound *= amplificationFactor;
    upperBound *= amplificationFactor;
    int radius = amplificationFactor;

    g.setColor(Color.RED);
    g.fillOval(leftBound + width/2, upperBound + height/2, radius, radius);
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
      StringBuffer sb = new StringBuffer();
      sb.append(room.getName());
      sb.append(", index: ");
      sb.append(room.getIndex());
      g.drawString(sb.toString(), leftBound + width / 4, upperBound + height / 2);
    }
  }

  public void addPlayer(PlayerCharacter player) {
    players.add(new Player(player));
  }

  class Player extends JComponent {
    private Character character;

    public Player(Character character) {
      this.character = character;
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
      g.drawOval(leftBound, upperBound, radius, radius);

      // Draw the text in the center
      String text = "" + character.getIndex();
      FontMetrics fontMetrics = g.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(text);
      int textHeight = fontMetrics.getHeight();
      int textX = centerX - textWidth / 2;
      int textY = centerY + textHeight / 2;

      g.drawString(text, textX, textY);
    }

    public String getPlayerName() {
      return character.getName();
    }
  }
}
