package controller;

import command.*;
import model.GamingModel;
import model.Model;
import view.GameBoardPanel;
import view.View;
import world.PlayerCharacter;
import world.Room;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.function.Function;

public class ViewController implements Controller,  Features {
  private  View view;
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
    System.out.println("startNewGame");
    model = new GamingModel(filePath, maxTurns);
    view.setModel(model);
    view.paintLayout();
    view.refresh();
  }

  @Override
  public void startGame() {
    if(model.getTurn()== null){
      JOptionPane.showMessageDialog(null, "No player"
          + ", please add player first.");
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
    if(model == null) {
      throw new IllegalStateException("Please click Game -> New Game to start a new game with "
          + "a world specification first.");
    }
    if(model.isGameStart()) {
      throw new IllegalStateException("Cannot add player after the game has started.");
    }
    if(capacity < 0 || index < 0) {
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
    PlayerCharacter player = model.getTurn();
    Room currentRoom = player.getRoom();
    if(currentRoom.isNeighbour(room) != 0) {
      model.move(player, room);
      model.passTurn();
      view.refresh();
    }
    else{
      throw new IllegalStateException("Cannot move to such room, it is not neighbour to the player");
    }
  }

  @Override
  public void executeCommand(int key, GameBoardPanel panel) {
    if(knownCommands.containsKey(key)) {
      GamingCommand command = knownCommands.get(key).apply(panel);
      try {
        command.execute(model);
      } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e){
        JOptionPane.showMessageDialog(panel, e.getMessage());
      }
      commands.push(command);
      view.refresh();
    }
  }

}
