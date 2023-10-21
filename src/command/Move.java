package command;

import model.GamingModel;
import model.Model;
import world.Character;
import world.PlayerCharacter;
import world.Room;

/**
 * The move to neighbour command.
 */
public class Move implements GamingCommand {
  private Character player;
  private int direction;
  private int index;

  /**
   * The constructor
   *
   * @param player    is the player to be moved.
   * @param direction is the direction of the moving.
   * @param index     is the index of room to move.
   */
  public Move(Character player, int direction, int index) {
    if (player == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.direction = direction;
    this.index = index;
  }

  @Override
  public void execute(Model m) {
    m.moveToNeighbour(player, direction, index);
  }
}
