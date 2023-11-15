package command;

import model.Model;
import world.PlayerCharacter;

/**
 * Attempt to kill the doctor.
 */
public class Attempt implements GamingCommand {
  private PlayerCharacter player;
  private int index;

  /**
   * Construct a Attempt class.
   *
   * @param player the player
   * @param index  the index of item
   */
  public Attempt(PlayerCharacter player, int index) {
    this.player = player;
    this.index = index;
  }

  @Override
  public void execute(Model m) {
    try {
      m.attempt(player, index);
    } catch (IllegalStateException e) {
      m.passTurn();
      throw new IllegalStateException("Attempt failed, be seen by others.");
    }

    m.passTurn();
  }
}
