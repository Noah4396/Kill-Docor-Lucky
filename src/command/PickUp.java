package command;

import controller.GamingModel;
import world.Character;
import world.PlayerCharacter;

public class PickUp implements GamingCommand{
  private PlayerCharacter player;
  private int index;

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
