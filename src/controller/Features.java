package controller;

import view.GameBoardPanel;
import world.Room;

public interface Features {
  /**
   * Start a new game.
   *
   * @param filePath the path to the file containing the game configuration
   * @param maxTurns the maximum number of turns allowed in the game
   */
  void startNewGame(String filePath, int maxTurns);

  /**
   * Start the game.
   */
  void startGame();

  /**
   * Add a player to the game.
   *
   * @param name the name of the player
   * @param capacity the capacity of the player
   * @param index the index of the room the player is in
   * @param isComputer whether the player is a computer
   */
  void addPlayer(String name, int capacity, int index, boolean isComputer);

  /**
   * Move the player to the given room.
   *
   * @param room the room to move the player to
   */
  void movePlayer(Room room);


  /**
   * Execute the command.
   * @param key
   */
  void executeCommand(int key, GameBoardPanel panel);
}
