package view.menu;

import controller.Features;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * A listener for the add player menu item.
 */
public class AddPlayerListener implements ActionListener {
  private Features features;

  /**
   * Construct an add player listener.
   * @param features the features
   */
  public AddPlayerListener(Features features) {
    this.features = features;
  }

  public void promptForPlayerInfo() {
    String name = JOptionPane.showInputDialog("Enter player name:");
    String capacityInput = JOptionPane.showInputDialog("Enter player capacity:");
    String roomIndex = JOptionPane.showInputDialog("Enter player room index:");
    Object[] options = {"Human", "Computer"};
    int playerTypeChoice = JOptionPane.showOptionDialog(
        null,
        "Select player type:",
        "Player Type",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );
    boolean isComputerPlayer = playerTypeChoice == 1;

    int capacity = 0;
    int index = 0;
    try {
      capacity = Integer.parseInt(capacityInput);
      index = Integer.parseInt(roomIndex);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
      return;
    }

    try{
      features.addPlayer(name, capacity, index, isComputerPlayer);
    } catch (IllegalArgumentException | IllegalStateException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    promptForPlayerInfo();
  }
}
