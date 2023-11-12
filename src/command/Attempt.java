package command;

import model.Model;
import world.PlayerCharacter;

public class Attempt implements GamingCommand{
  private PlayerCharacter player;
  private int index;

  public Attempt(PlayerCharacter player, int index) {
    this.player = player;
    this.index = index;
  }

  @Override
  public void execute(Model m) {
    m.attempt(player, index);
    m.passTurn();
  }
}
