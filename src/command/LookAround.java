package command;

import model.GamingModel;
import world.PlayerCharacter;

import java.io.IOException;

/**
 * Look around command.
 */
public class LookAround implements GamingCommand {
  private PlayerCharacter player;
  private final Appendable out;

  public LookAround(PlayerCharacter player, Appendable out) {
    if (player == null || out == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.out = out;
  }

  @Override
  public void execute(GamingModel m) {
    try {
      out.append(m.lookAround(player));
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
