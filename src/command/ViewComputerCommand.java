package command;

import model.Model;
import view.GameBoardPanel;
import world.PlayerCharacter;

import javax.swing.*;

public class ViewComputerCommand implements GamingCommand {
  private PlayerCharacter player;
  private GameBoardPanel panel;

  public ViewComputerCommand(PlayerCharacter player, GameBoardPanel panel) {
    this.player = player;
    this.panel = panel;
  }

  @Override
  public void execute(Model m) {
    if (!player.isComputer()) {
      throw new IllegalStateException(
          "This is not a computer, " + "please enter 1-3 to execute the command.");
    }
    StringBuffer sb = new StringBuffer();
    m.computerCommand(player, sb);
    m.passTurn();
    JOptionPane.showMessageDialog(panel, sb.toString());
  }
}
