package controller;

import command.Attempt;
import command.GamingCommand;
import command.PickUp;
import command.ViewComputerCommand;
import command.ViewLookAround;
import model.GamingModel;
import model.Model;
import view.GameBoardPanel;
import view.View;
import world.PlayerCharacter;
import world.Room;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;


public class ViewController implements Controller, Features {
  private View view;
  private Model model;
  Map<Integer, Function<GameBoardPanel, GamingCommand>> knownCommands;
  Stack<GamingCommand> commands;

  public ViewController(View view, Model model) {
    this.view = view;
    this.model = model;
    knownCommands = new HashMap<>();
    commands = new Stack<>();
  }

  @Override
  public void playGame(Model m) {
    view.makeVisible();
  }

  @Override
  public void startNewGame(String filePath, int maxTurns) {
    model = new GamingModel(filePath, maxTurns);
    view.setModel(model);
    view.paintLayout();
    view.refresh();
  }

  @Override
  public void startGame() {
    if (model.getTurn() == null) {
      JOptionPane.showMessageDialog(null, "No player" + ", please add player first.");
      return;
    }
    if (model.isGameStart()) {
      JOptionPane.showMessageDialog(null, "Game has already started.");
      return;
    }
    model.setGameStart(true);
    knownCommands.put(KeyEvent.VK_1, s -> new PickUp(model.getTurn(), s.getRoomItemIndex()));
    knownCommands.put(KeyEvent.VK_2, s -> new ViewLookAround(model.getTurn(), s));
    knownCommands.put(KeyEvent.VK_3, s -> new Attempt(model.getTurn(), s.getPlayerItemIndex()));
    knownCommands.put(KeyEvent.VK_C, s -> new ViewComputerCommand(model.getTurn(), s));
    view.setPanelFeatures(this);
    view.refresh();
    JOptionPane.showMessageDialog(null, "Game started.");
  }

  @Override
  public void addPlayer(String name, int capacity, int index, boolean isComputer) {
    if (model == null) {
      throw new IllegalStateException("Please click Game -> New Game to start a new game with "
          + "a world specification first.");
    }
    if (model.isGameStart()) {
      throw new IllegalStateException("Cannot add player after the game has started.");
    }
    if (model.getPlayers().size() == 10) {
      throw new IllegalStateException("Cannot add more than 10 players.");
    }
    if (capacity < 0 || index < 0) {
      throw new IllegalArgumentException("Invalid input. Please enter a valid number.");
    }
    PlayerCharacter player = new PlayerCharacter(name, model.getPlayers().size(), capacity);
    model.addPlayer(player, index);
    if (isComputer) {
      player.setAsComputer();
    }
    view.addPlayer(player);
    view.refresh();
  }

  @Override
  public void movePlayer(Room room) {
    if (model.isGameOver()) {
      JOptionPane.showMessageDialog(null, "Game is over, please start a new game.");
      return;
    }
    PlayerCharacter player = model.getTurn();
    Room currentRoom = player.getRoom();
    if (currentRoom.isNeighbour(room) != 0) {
      model.move(player, room);
      model.passTurn();
      view.refresh();
    } else {
      throw new IllegalStateException(
          "Cannot move to such room, it is not neighbour to the player");
    }
  }

  @Override
  public void executeCommand(int key, GameBoardPanel panel) {
    if (model.isGameOver()) {
      JOptionPane.showMessageDialog(null, "Game is over, please start a new game.");
      return;
    }
    if (knownCommands.containsKey(key)) {
      GamingCommand command = knownCommands.get(key).apply(panel);
      try {
        command.execute(model);
      } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
        JOptionPane.showMessageDialog(panel, e.getMessage());
      }
      commands.push(command);
      view.refresh();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ViewController that = (ViewController) o;
    return Objects.equals(view, that.view) && Objects.equals(model, that.model) && Objects.equals(
        knownCommands, that.knownCommands) && Objects.equals(commands, that.commands);
  }

  @Override
  public int hashCode() {
    return Objects.hash(view, model, knownCommands, commands);
  }

  @Override
  public String toString() {
    return hashCode() + "";
  }
}
