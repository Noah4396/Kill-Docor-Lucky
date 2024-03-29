package view;

import controller.Controller;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import model.ReadOnlyModel;
import world.PlayerCharacter;

/**
 * The view for the game.
 */
public class GamingView extends JFrame implements View {
  private ReadOnlyModel model;
  private GameBoardPanel boardPanel;
  private final JLabel turnLabel;
  private final GamingMenu gameMenu;
  private final JLabel guideLabel;
  private String labelText;

  /**
   * Construct a gaming view.
   *
   * @param model the model
   */
  public GamingView(ReadOnlyModel model) {
    this.model = model;
    setTitle("Kill Doctor Lucky");
    setSize(1400, 1200);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);

    setLayout(new BorderLayout());

    gameMenu = new GamingMenu(model);
    setJMenuBar(gameMenu);

    turnLabel = new JLabel("", SwingConstants.LEADING);
    add(turnLabel, BorderLayout.EAST); // Move the label to the right side

    guideLabel = new JLabel(
        "Click menu Game --> NewGame " + "to start a new game with a world specification.");
    add(guideLabel, BorderLayout.CENTER);

    boardPanel = null;
    if (model != null) {
      paintLayout();
    }
  }

  @Override
  public void addClickListener(Features listener) {

  }

  @Override
  public void addKeyListener(Features listener) {

  }

  @Override
  public void refresh() {
    // Revalidate and repaint the container to reflect the changes
    revalidate();
    repaint();

    if (model != null) {
      if (!model.isGameStart()) {
        labelText = "Click menu Game --> Add Player to add player";
        labelText += "<br>After adding all of the player, "
            + "click menu Game --> Start Game to start the game.";
      } else {
        if (model.isGameOver()) {
          labelText = "Game Over!";
          if (model.getWinner() != null) {
            labelText += "<br>Winner: " + model.getWinner().getName();
            JOptionPane.showMessageDialog(this, "Winner: " + model.getWinner().getName());
          } else {
            labelText += "<br>Reach the max turn, and no winner.";
            JOptionPane.showMessageDialog(this, "Reach the max turn, and no winner.");
          }
        } else {
          labelText = "The red circle is the target and the number on it is the health.";
          labelText += "<br>The yellow circle is the current player and its player index.";
          labelText += "<br>The blue circle is the player not in turn and its player index.";
          labelText += "<br>Try your best to kill the target!.";
          labelText += "<br>";
          labelText +=
              "<br>Current turn: " + (model.getTotalTurn() + 1) + ", MaxTurn:" + model.getMaxTurn();
          labelText += "<br>Current player: " + model.getTurn().getName() + ", player index: "
              + model.getTurn().getIndex();
          labelText += "<br>Player Type: " + (model.getTurn().isComputer() ? "Computer" : "Human");

          labelText += "<br>";
          labelText +=
              "<br>Items in the room: " + "<br>" + model.getTurn().getRoom().getItemsString();

          labelText += "<br>";
          labelText += "<br>Click in the circle of room to move to neighbour.";
          labelText += "<br> Enter 1 to pick up an item in the room.";
          labelText += "<br> Enter 2 to look around.";
          labelText += "<br> Enter 3 to attempt to kill the target.";
          labelText += "<br> Enter c to let the computer player execute its command.";
        }
      }
    }

    turnLabel.setText("<html>" + labelText + "</html>");
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void setModel(ReadOnlyModel model) {
    this.model = model;
  }

  @Override
  public void paintLayout() {
    // Remove the existing boardPanel, if any
    if (boardPanel != null) {
      remove(boardPanel);
    }

    // Create and set up the new boardPanel
    boardPanel = new GameBoardPanel(model);
    boardPanel.setLayout(new GridLayout(0, 1));
    guideLabel.setText("");
    // Add the new boardPanel to the container
    add(boardPanel, BorderLayout.CENTER);

  }

  @Override
  public void addPlayer(PlayerCharacter player) {
    boardPanel.addPlayer(player);
  }

  @Override
  public void setMenuFeatures(Features features) {
    gameMenu.addListener(features);
  }

  @Override
  public void setPanelFeatures(Features features) {
    boardPanel.addListener(features);
  }
}
