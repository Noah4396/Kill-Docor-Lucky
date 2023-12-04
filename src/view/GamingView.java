package view;

import controller.Controller;
import controller.Features;
import model.ReadOnlyModel;
import world.PlayerCharacter;

import javax.swing.*;
import java.awt.*;

public class GamingView extends JFrame implements View {
  private ReadOnlyModel model;
  private GameBoardPanel boardPanel;
  private final JLabel turnLabel;
  private String turnInfo;
  private final GamingMenu gameMenu;
  private final JLabel guideLabel;

  public GamingView(ReadOnlyModel model) {
    turnInfo = "1";
    this.model = model;
    setTitle("Kill Doctor Lucky");
    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);

    setLayout(new BorderLayout());

    gameMenu = new GamingMenu(model);
    setJMenuBar(gameMenu);

    turnLabel = new JLabel("", SwingConstants.LEADING);
    add(turnLabel, BorderLayout.EAST); // Move the label to the right side


    guideLabel = new JLabel("Click menu Game --> NewGame to start.");
    add(guideLabel, BorderLayout.CENTER);


    boardPanel = null;
  }


  @Override
  public void addClickListener(Features listener) {

  }

  @Override
  public void addKeyListener(Features listener) {

  }

  @Override
  public void refresh() {
    boardPanel.repaint();
    if (model.isGameOver()) {
      turnLabel.setText("Game over!");
    } else {
      turnLabel.setText("Current turn: " + turnInfo);
    }
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

    // Revalidate and repaint the container to reflect the changes
    revalidate();
    repaint();
  }


  @Override
  public void addPlayer(PlayerCharacter player) {
    boardPanel.addPlayer(player);
  }

  @Override
  public void setFeatures(Features features) {
    gameMenu.addListener(features);
  }
}
