package command;

import model.GamingModel;
import model.Model;
import world.PlayerCharacter;

/**
 * The computer command.
 */
public class ComputerCommand implements GamingCommand {
  private PlayerCharacter player;
  private final Appendable out;

  /**
   * Constructor.
   *
   * @param player the computer player.
   */
  public ComputerCommand(PlayerCharacter player, Appendable out) {
    if (!player.isComputer()) {
      throw new IllegalArgumentException("Not a computer");
    }
    this.player = player;
    this.out = out;
  }

  @Override
  public void execute(Model m) {
    m.computerCommand(player, out);
  }
}
