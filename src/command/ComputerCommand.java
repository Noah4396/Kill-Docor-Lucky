package command;

import model.GamingModel;
import world.PlayerCharacter;

/**
 * The computer command.
 */
public class ComputerCommand implements GamingCommand {
  private PlayerCharacter player;

  /**
   * Constructor.
   * @param player the computer player.
   */
  public ComputerCommand(PlayerCharacter player) {
    if (!player.isComputer()) {
      throw new IllegalArgumentException("Not a computer");
    }
    this.player = player;
  }

  @Override
  public void execute(GamingModel m) {
    m.computerCommand(player);
  }
}
