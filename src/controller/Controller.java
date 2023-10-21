package controller;

import model.GamingModel;
import model.Model;

/**
 * Represents a Controller for Kill Doctor Lucky: handle user moves by executing them using the
 * model; convey outcomes to the user in some form.
 */
public interface Controller {
  /**
   * Execute a single game of kill doctor lucky given a gaming Model. When the game is over, the
   * playGame method ends.
   *
   * @param m a non-null kill doctor lucky Model
   */
  void playGame(Model m);
}
