package controller;

import model.GamingModel;
import model.Model;
import view.View;

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
}
