package controller;

import model.Model;
import view.View;

public class ViewController implements Controller{
  private  View view;
  private Model model;
  @Override
  public void playGame(Model m) {
    view.makeVisible();
  }
}
