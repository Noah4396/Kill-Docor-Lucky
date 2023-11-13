package controller;

import command.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;
import model.Model;
import world.PlayerCharacter;

/**
 * The gaming console controller.
 */
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
    Map<String, Function<Scanner, GamingCommand>> knownCommands = new HashMap<>();
    Stack<GamingCommand> commands = new Stack<>();
    knownCommands.put("1", s -> new Move(m.getTurn(), s.nextInt(), s.nextInt()));
    knownCommands.put("2", s -> new PickUp(m.getTurn(), s.nextInt()));
    knownCommands.put("3", s -> new LookAround(m.getTurn(), this.out));
    knownCommands.put("4", s -> new MovePet(m.getTurn(), s.nextInt(), s.nextInt()));
    knownCommands.put("5", s -> new Attempt(m.getTurn(), s.nextInt()));
    if (m == null) {
      throw new IllegalArgumentException("Invalid Model");
    }
    if (!gameInitialize(m)) {
      return;
    }
    outputString("Game start! The players are listed as follow: \n");
    outputString(m.displayers());

    while (!m.isGameOver()) {
      GamingCommand c;
      if (m.getTurn().isComputer()) {
        c = new ComputerCommand(m.getTurn(), this.out);
        printDivider();
        c.execute(m);
        printDivider();
      } else {
        printPrompt(m);
        if (!scan.hasNext()) {
          outputString("No input ang longer, game over\n");
          return;
        }
        String in = scan.next();
        if ("q".equalsIgnoreCase(in) || "quit".equalsIgnoreCase(in)) {
          outputString("Game quit!\n");
          return;
        }

        Function<Scanner, GamingCommand> cmd = knownCommands.getOrDefault(in, null);
        if (cmd == null) {
          outputString("Invalid option, please enter again.\n");
        } else {
          try {
            c = cmd.apply(scan);
            commands.add(c);
            c.execute(m);
          } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
            outputString(e.getMessage() + "\n");
          }
        }
      }
//      outputString("Now the status of players are shown as follow:\n");
//      outputString(m.displayers() + "\n");
//      outputString("After player operation, the status of target and pet are shown as follow:\n");
//      outputString(m.displayTargetAndPet() + "\n");
      if(m.isGameOver()){
        outputString("The game is over, the winner is: " + m.getTurn().getName() + "\n");
        return;
      }
    }
    outputString("Reach the max turn, and the game end!\n");
  }

  private void printDivider() {
    outputString("---------------------------------\n");
  }

  private void outputString(String s) {
    try {
      out.append(s);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  private void printPrompt(Model m) {
    printDivider();
    outputString("Now we are in the " + (m.getTotalTurn() + 1) + " turn\n");
    outputString("The player in current turn is: " + m.getTurn().getName() + "\n\n");
    outputString("The items of the player are:\n");
    outputString(m.getTurn().displayItems() + "\n\n");
    outputString("The information of the room that the player is in:\n");
    outputString(m.getTurn().getRoom().toString() + "\n\n");
    outputString("The status of target and pet are:\n");
    outputString(m.displayTargetAndPet() + "\n");
    outputString(
        "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n");
    outputString("Enter 2 to pick up an item in the room. Then enter the index of the item\n");
    outputString("Enter 3 to look around\n");
    outputString(
        "Enter 4 to move the pet. Then enter the direction and index of the neighbour\n");
    outputString("Enter 5 to attempt to kill the target. Then enter the "
        + "index of item you want to use(enter 0 if you do not have any item)\n");
    printDivider();
  }

  private boolean gameInitialize(Model m) {
    outputString("Welcome to the kill doctor lucky game\n");
    outputString("The rooms are initialized as:\n");
    outputString(m.displayRooms());
    outputString("Enter 1 to add a player; Enter 2 to add a computer player; "
        + "Enter 3 to start the game; Enter 4 to quit the game\n");
    while (scan.hasNext()) {
      switch (scan.next()) {
        case "1":
          getPlayerInput(m, false);
          break;
        case "2":
          getPlayerInput(m, true);
          break;
        case "3":
          if (players.isEmpty()) {
            outputString("There are no player in the game, please create at least one player\n");
          } else {
            return true;
          }
          break;
        case "4":
          outputString("Ending game....\n");
          return false;
        default:
          outputString("Invalid option, please enter again\n");
      }
      outputString("Enter 1 to add a player; Enter 2 to add a computer player; "
          + "Enter 3 to start the game; Enter 4 to quit the game\n");
    }
    return false;
  }

  private void getPlayerInput(Model m, boolean isComputer) {
    outputString("Please enter the name of the player:\n");
    final String name = scan.next();
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
      m.addPlayer(player, roomIndex);
      players.add(player);
    } catch (InputMismatchException | IllegalArgumentException e) {
      outputString("Invalid room index or name duplicated!\n");
      return;
    }
    if (isComputer) {
      player.setAsComputer();
    }
    outputString("Add player successfully, the current player information:\n");
    outputString(m.displayers() + "\n");

  }
}
