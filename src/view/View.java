package view;

import controller.Features;
import model.ReadOnlyModel;
import world.PlayerCharacter;

/**
 * A view for the game: display the game board and provide visual interface for users.
 */
public interface View {

  /**
   * Set up the controller to handle click events in this view.
   * @param listener the controller
   */
  void addClickListener(Features listener);

  /**
   * Set up the controller to handle key events in this view.
   * @param listener the controller
   */
  void addKeyListener(Features listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Set the features of the view.
   * @param features the features to set
   */
  void setMenuFeatures(Features features);

  /**
   * Set the features of the panel.
   * @param features the features to set
   */
  void setPanelFeatures(Features features);

  /**
   * Set the model of the view.
   * @param model the model to set
   */
  public void setModel(ReadOnlyModel model);

  /**
   * Paint the layout of the view.
   */
  public void paintLayout();

  /**
   * Add a player to the view.
   * @param player the player to add
   */
  public void addPlayer(PlayerCharacter player);
}
