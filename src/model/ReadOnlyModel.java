package model;

import world.Character;
import world.PlayerCharacter;

/**
 * The read-only model of the game.
 */
public interface ReadOnlyModel {

  /**
   * Look around the room.
   * @param c the character
   * @return the string of the room
   */
  String lookAround(Character c);

  /**
   * Get Winner
   *
   * @return the player character
   */
  PlayerCharacter getWinner();

  /**
   * Get the player character.
   *
   * @return the player character
   */
  int getTotalTurn();

  /**
   * Get the player character.
   *
   * @return the player character
   */
  PlayerCharacter getTurn();

  /**
   * Get the player character.
   *
   * @return the player character
   */
  String displayRooms();

  /**
   * Get the player character.
   *
   * @return the player character
   */
  String displayers();

  /**
   * If the game is over.
   *
   * @return true if the game is over
   */
  boolean isGameOver();

  /**
   * display the room.
   *
   * @return the room
   */
  String displayRoom(int index);

  /**
   * Get the player character.
   *
   * @return the player character
   */
  String displayTargetAndPet();
}
