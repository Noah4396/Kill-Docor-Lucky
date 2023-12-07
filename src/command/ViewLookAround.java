package command;

import model.Model;
import view.GameBoardPanel;
import javax.swing.JOptionPane;
import world.PlayerCharacter;

import java.io.IOException;

/**
 * A command to view the computer's command.
 */
public class ViewLookAround implements GamingCommand {
  private PlayerCharacter player;
  private GameBoardPanel panel;

  /**
   * Construct a view computer command.
   * @param player the player
   * @param panel the panel
   */
  public ViewLookAround(PlayerCharacter player, GameBoardPanel panel) {
    this.player = player;
    this.panel = panel;
  }

  @Override
  public void execute(Model m) {
    if (player.isComputer()) {
      throw new IllegalStateException("Please enter c to make the computer execute its command.");
    }
    try {
      JOptionPane.showMessageDialog(panel, m.lookAround(player));
      m.passTurn();
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
}
