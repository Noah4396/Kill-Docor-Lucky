package command;

import model.Model;
import view.GameBoardPanel;
import world.PlayerCharacter;

import javax.swing.*;

public class ViewLookAround implements GamingCommand {
  private PlayerCharacter player;
  private GameBoardPanel panel;

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
    } catch (Exception e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
}
