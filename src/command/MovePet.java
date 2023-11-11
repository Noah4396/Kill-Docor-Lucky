package command;

import model.Model;
import world.Character;

public class MovePet implements GamingCommand{
  private Character player;
  private int direction;
  private int index;

  /**
   * The constructor.
   *
   * @param player    is the player to be moved.
   * @param direction is the direction of the moving.
   * @param index     is the index of room to move.
   */
  public MovePet(Character player, int direction, int index) {
    if (player == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.direction = direction;
    this.index = index;
  }

  @Override
  public void execute(Model m) {
    m.movePet(player, direction, index);
  }
}
