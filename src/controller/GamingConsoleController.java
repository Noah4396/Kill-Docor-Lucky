package controller;

import command.GamingCommand;
import model.GamingModel;
import model.Model;
import world.PlayerCharacter;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class GamingConsoleController implements Controller {
  private final Appendable out;
  private final Scanner scan;
  private ArrayList<PlayerCharacter> players;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public GamingConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    players = new ArrayList<>();
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(Model m) {
    if (m == null) {
      throw new IllegalArgumentException("Invalid Model");
    }
    if (!gameInitialize(m)) {
      return;
    }
  }

  private void outputString(String s) {
    try {
      out.append(s);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  private void printPrompt() {

  }

  private boolean gameInitialize(Model m) {
    outputString("Welcome to the kill doctor lucky game\n");
    outputString("The rooms are initialized as:\n");
    outputString(m.displayRooms());
    while (true) {
      outputString(
          "Enter 1 to add a player; Enter 2 to start the game; Enter 3 to quit the game\n");
      switch (scan.next()) {
        case "1":
          getPlayerInput(m);
          break;
        case "2":
          if (players.isEmpty()) {
            outputString("There are no player in the game, please create at least one player\n");
          } else {
            return true;
          }
          break;
        case "3":
          outputString("Ending game....\n");
          return false;
        default:
          outputString("Invalid option, please enter again\n");
      }
    }
  }

  private void getPlayerInput(Model m) {
    outputString("Please enter the name of the player:\n");
    String name = scan.next();
    outputString("Please enter the capacity:\n");

    int capacity = 1;
    try {
      capacity = scan.nextInt();
    } catch (InputMismatchException e) {
      outputString("Invalid input, the capacity should be a int.\n");
      return;
    }
    outputString("Please enter the index of the room that the player is in:\n");

    int roomIndex = 0;
    PlayerCharacter player = new PlayerCharacter(name, players.size(), capacity);
    try {
      roomIndex = scan.nextInt();
      m.addPlayer(player,roomIndex);
      players.add(player);
    } catch (InputMismatchException | IllegalArgumentException e) {
      outputString("Invalid room index or name duplicated!\n");
      return;
    }
    outputString("Add player successfully, the current player information:\n");
    outputString(m.displayers() + "\n");

  }
}
