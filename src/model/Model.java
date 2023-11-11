package model;

import world.Character;
import world.PlayerCharacter;

/**
 * The model of game.
 */
public interface Model {
  /**
   * Execute computer command.
   *
   * @param player the computer player.
   * @param out the output.
   */
  public void computerCommand(PlayerCharacter player, Appendable out);

  /**
   * Look around.
   *
   * @param c the character that looks around.
   * @return information of other players.
   */
  public String lookAround(Character c);

  /**
   * Move character to neighbour.
   *
   * @param c         the moved character.
   * @param direction the moving direction.
   * @param index     the index of room in direction.
   */
  public void moveToNeighbour(Character c, int direction, int index);

  /**
   * Move the pet to the neighbour.
   * @param c the character who move pet
   * @param direction the direction
   * @param index the index
   */
  public void movePet(Character c, int direction, int index);
  /**
   * Move the target.
   */
  public void moveTarget();


  /**
   * Move the pet.
   */
  public void movePetDepthFirst();

  /**
   * Get the current turn number.
   *
   * @return the current turn.
   */
  int getTotalTurn();

  /**
   * Let the player pick up an item from its room, if the room do not have an item or the player
   * cannot pick more item, it will throw IllegalStateException.
   *
   * @param p     the player that will pick item
   * @param index the item index;
   */
  public void pickUpItem(PlayerCharacter p, int index);

  /**
   * Add a player playing the game.
   *
   * @param p is the player added to the game.
   * @param roomIndex is the index of the room that the player is added to.
   */
  public void addPlayer(PlayerCharacter p, int roomIndex);

  /**
   * Get the current turn.
   *
   * @return the player that is in the turn.
   */
  public PlayerCharacter getTurn();

  /**
   * Display all the rooms.
   *
   * @return the displayedRooms.
   */
  public String displayRooms();

  /**
   * Display all players.
   *
   * @return the player information.
   */
  public String displayers();

  /**
   * Attempt to kill the target.
   *
   * @param player the player who attempt to kill.
   * @param index  the index of weapon.
   */
  void attempt(PlayerCharacter player, int index);

  /**
   * Pass the turn to next player.
   */
  public void passTurn();

  /**
   * check if it is game over.
   *
   * @return true if game over.
   */
  public boolean isGameOver();

  /**
   * Display room of index.
   *
   * @param index the index of room
   * @return the display.
   */
  public String displayRoom(int index);

  /**
   * Display the target and pet.
   *
   * @return the display.
   */
  public String displayTargetAndPet();
}
