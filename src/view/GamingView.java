package view;

import controller.Controller;
import model.ReadOnlyModel;

import javax.swing.*;
import java.awt.*;

public class GamingView extends JFrame implements View {
  private final ReadOnlyModel model;
  private final GameBoardPanel boardPanel;
  private final JLabel turnLabel;

  public GamingView(ReadOnlyModel model) {
    this.model = model;
    setTitle("Kill Doctor Lucky");
    setSize(1000, 1000);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    turnLabel = new JLabel("Current turn: ", SwingConstants.CENTER);
    boardPanel = new GameBoardPanel(model);
    add(turnLabel, BorderLayout.NORTH);
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
