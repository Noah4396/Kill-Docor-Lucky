package command;

import model.GamingModel;
import model.Model;
import world.Character;
import world.PlayerCharacter;

/**
 * The pickup command.
 */
public class PickUp implements GamingCommand {
  private PlayerCharacter player;
  private int index;

  /**
   * The constructor.
   *
   * @param player the player.
   * @param index  the index of item.
   */
  public PickUp(PlayerCharacter player, int index) {
    if (player == null) {
      throw new IllegalArgumentException("Invalid input.");
    }
    this.player = player;
    this.index = index;
  }

  @Override
  public void execute(Model m) {
    if (player.isComputer()) {
      throw new IllegalStateException("Please enter c to make the computer execute its command.");
    }
    m.pickUpItem(player, index);
    m.passTurn();
  }
}
