package view;

import javax.swing.*;
import model.ReadOnlyModel;
/**
 * Panel for the game board.
 */
public class GameBoardPanel extends JPanel {
  private final ReadOnlyModel model;

  /**
   * Constructor.
   *
   * @param model the model to use
   */
  public GameBoardPanel(ReadOnlyModel model) {
    this.model = model;
  }
}
