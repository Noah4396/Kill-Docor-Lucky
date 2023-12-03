package view.menu;

import controller.Features;
import model.Model;
import model.ReadOnlyModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddPlayerListener implements ActionListener {
  private Features features;


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
    }

    try{
      features.addPlayer(name, capacity, index, isComputerPlayer);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(null, "Invalid room index. Please enter a valid room index.");
    } catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null, "Name duplicated. Please enter a valid name.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    promptForPlayerInfo();
  }
}