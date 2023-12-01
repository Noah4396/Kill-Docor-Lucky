package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.ReadOnlyModel;
import world.Room;

/**
 * Panel for the game board.
 */
public class GameBoardPanel extends JPanel {
  private final ReadOnlyModel model;
  private final ArrayList<Room> rooms;

  /**
   * Constructor.
   *
   * @param model the model to use
   */
  public GameBoardPanel(ReadOnlyModel model) {
    this.model = model;
    this.rooms = model.getRooms();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintRooms(g);
  }

  private void paintRooms(Graphics g) {
    int cellWidth = getWidth();
    int cellHeight = getHeight();
    int maxWidth = 0;
    int maxHeight = 0;
    for(Room room : rooms){
      maxHeight = Math.max(maxHeight, room.getLowerCorner());
      maxWidth = Math.max(maxWidth, room.getRightCorner());
    }
    int amplificationFactor = Math.min(cellWidth / (maxWidth + 1), cellHeight / (maxHeight + 1));
    for(Room room : rooms) {
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
      g.drawString(room.getName(), leftBound + width / 4, upperBound + height / 2);
    }
  }
}
