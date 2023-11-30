package model;

import world.Character;
import world.PlayerCharacter;

/**
 * The model of game.
 */
public interface Model extends ReadOnlyModel {

  /**
   * Computer command.
   * @param player the player
   * @param out the appendable
   */
  void computerCommand(PlayerCharacter player, Appendable out);

  /**
   * Move to neighbour.
   * @param c the character
   * @param direction the direction
   * @param index the index of room
   */
  void moveToNeighbour(Character c, int direction, int index);

  /**
   * Move to neighbour.
   * @param c the character
   * @param direction the direction
   * @param index the index of room
   */
  void movePet(Character c, int direction, int index);

  /**
   * MoveTarget.
   */
  void moveTarget();

  /**
   * Move pet depth first.
   */
  void movePetDepthFirst();

  /**
   * pick up item.
   * @param p the player
   * @param index the index of item
   */
  void pickUpItem(PlayerCharacter p, int index);

  /**
   * add player.
   * @param p the player
   * @param roomIndex the index of room
   */
  void addPlayer(PlayerCharacter p, int roomIndex);

  /**
   * attempt.
   * @param player the player
   * @param index the index of item
   */
  void attempt(PlayerCharacter player, int index);

  /**
   * pass turn.
   */
  void passTurn();
}