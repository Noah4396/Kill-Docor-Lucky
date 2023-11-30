package view;

import controller.Controller;

/**
 * A view for the game: display the game board and provide visual interface for users.
 */
public interface View {

  /**
   * Set up the controller to handle click events in this view.
   * @param listener the controller
   */
  void addClickListener(Controller listener);

  /**
   * Set up the controller to handle key events in this view.
   * @param listener the controller
   */
  void addKeyListener(Controller listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
