package model;

import world.Character;
import world.PlayerCharacter;

public interface Model {
  /**
   * Execute computer command.
   * @param player the computer player.
   */
  public void computerCommand(PlayerCharacter player);

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
   */
  public void addPlayer(PlayerCharacter p, int roomIndex);

  /**
   * Get the current turn.
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
   * @return the players information.
   */
  public String displayers();
}
