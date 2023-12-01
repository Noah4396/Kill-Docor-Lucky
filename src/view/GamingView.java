package view;

import controller.Controller;
import model.ReadOnlyModel;

import javax.swing.*;
import java.awt.*;

public class GamingView extends JFrame implements View {
  private final ReadOnlyModel model;
  private final GameBoardPanel boardPanel;
  private final JLabel turnLabel;
  private String turnInfo;
  private final GamingMenu gameMenu;

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

    turnLabel = new JLabel("Current turn: " + turnInfo, SwingConstants.LEADING);
    add(turnLabel, BorderLayout.EAST); // Move the label to the right side
    turnLabel.setVerticalTextPosition(SwingConstants.TOP);
    turnLabel.setHorizontalTextPosition(SwingConstants.LEFT);

    boardPanel = new GameBoardPanel(model);
    boardPanel.setLayout(new GridLayout(0, 1)); // GridLayout with a single column
    add(boardPanel, BorderLayout.CENTER);
  }

  @Override
  public void addClickListener(Controller listener) {

  }

  @Override
  public void addKeyListener(Controller listener) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
