package view;

import controller.Features;
import controller.ViewController;
import model.ReadOnlyModel;
import view.menu.NewGameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamingMenu extends JMenuBar {
  private JMenuItem newGameMenuItem;
  private JMenuItem exitMenuItem;
  private ReadOnlyModel model;

  public GamingMenu(ReadOnlyModel model) {
    initializeMenu();
    this.model = model;
  }

  public void setModel(ReadOnlyModel model) {
    this.model = model;
  }

  private void initializeMenu() {
    JMenu gameMenu = new JMenu("Game");

    newGameMenuItem = new JMenuItem("New Game");
    exitMenuItem = new JMenuItem("Exit");

    gameMenu.add(newGameMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(exitMenuItem);

    add(gameMenu);

  }

  public void addListener(Features listener) {
    newGameMenuItem.addActionListener(new NewGameListener(listener));

    exitMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
  }
}
