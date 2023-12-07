package view.menu;

import controller.Features;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * A listener for the new game menu item.
 */
public class NewGameListener implements ActionListener {

  private Features features;

  /**
   * Construct a new game listener.
   * @param features the features
   */
  public NewGameListener(Features features) {
    this.features = features;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Prompt the user for the configuration file path
    String filePath = promptForFilePath();

    if (filePath != null) {
      // Prompt the user for the max turn number
      int maxTurns = promptForMaxTurns();

      // Start a new game with the provided configuration and max turn number
      if (maxTurns > 0) {
        // You need a method in your controller to start a new game with the given parameters
        // For example, assuming you have a method like startNewGame(String filePath, int maxTurns) in your controller
        features.startNewGame(filePath, maxTurns);
      } else {
        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid turn number.");
      }
    }
  }

  private String promptForFilePath() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    } else {
      return null; // User canceled the file selection
    }
  }

  private int promptForMaxTurns() {
    String input = JOptionPane.showInputDialog("Enter max turn number:");
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
      return -1; // Indicates an invalid input
    }
  }
}

