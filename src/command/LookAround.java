package command;

import java.io.IOException;
import model.GamingModel;
import model.Model;
import world.PlayerCharacter;

/**
 * Look around command.
 */
public class LookAround implements GamingCommand {
  private PlayerCharacter player;
  private final Appendable out;

  /**
   * The constructor.
   * @param player player that looks around.
   * @param out the appendable output.
   */
  public LookAround(PlayerCharacter player, Appendable out) {
    if (player == null || out == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.out = out;
  }

  @Override
  public void execute(Model m) {
    try {
      out.append(m.lookAround(player));
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
