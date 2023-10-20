package command;

import model.GamingModel;
import world.Character;
import world.PlayerCharacter;

/**
 * The pickup command.
 */
public class PickUp implements GamingCommand{
  private PlayerCharacter player;
  private int index;

  /**
   * The constructor.
   * @param player the player.
   * @param index the index of item.
   */
  public PickUp(PlayerCharacter player, int index) {
    if (player == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.index = index;
  }

  @Override
  public void execute(GamingModel m) {
    m.pickUpItem(player, index);
  }
}
