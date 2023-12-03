package controller;

import model.GamingModel;
import model.Model;
import view.View;
import world.PlayerCharacter;

public class ViewController implements Controller,  Features {
  private  View view;
  private Model model;

  public ViewController(View view, Model model) {
    this.view = view;
    this.model = model;
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
    model.setGameStart(true);
    view.refresh();
  }

  @Override
  public void addPlayer(String name, int capacity, int index, boolean isComputer) {
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
}
