package model;

import java.util.ArrayList;
import world.Character;
import world.PlayerCharacter;
import world.Room;

/**
 * The read-only model of the game.
 */
public interface ReadOnlyModel {

  /**
   * Look around the room.
   *
   * @param c the character
   * @return the string of the room
   */
  String lookAround(Character c);

  /**
   * Get Winner.
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

  /**
   * Get all rooms.
   *
   * @return the array list of rooms
   */
  ArrayList<Room> getRooms();

  /**
   * Get if the game start.
   *
   * @return the boolean
   */
  boolean isGameStart();

  /**
   * get target.
   *
   * @return the target
   */
  Character getTarget();

  /**
   * get players.
   *
   * @return the players
   */
  ArrayList<PlayerCharacter> getPlayers();

  /**
   * get max turn.
   *
   * @return the max turn
   */
  int getMaxTurn();
}
