package view;

import controller.Features;
import controller.ViewController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import model.ReadOnlyModel;
import view.menu.AddPlayerListener;
import view.menu.NewGameListener;

/**
 * The menu for the game.
 */
public class GamingMenu extends JMenuBar {
  private JMenuItem newGameMenuItem;
  private JMenuItem exitMenuItem;
  private JMenuItem startGameMenuItem;
  private JMenuItem addPlayerMenuItem;
  private ReadOnlyModel model;

  /**
   * Construct a gaming menu.
   *
   * @param model the model
   */
  public GamingMenu(ReadOnlyModel model) {
    initializeMenu();
    this.model = model;
  }

  /**
   * Set the model.
   *
   * @param model the model
   */
  public void setModel(ReadOnlyModel model) {
    this.model = model;
  }

  private void initializeMenu() {
    JMenu gameMenu = new JMenu("Game");

    newGameMenuItem = new JMenuItem("New Game");
    exitMenuItem = new JMenuItem("Exit");
    startGameMenuItem = new JMenuItem("Start Game");
    addPlayerMenuItem = new JMenuItem("Add Player");

    gameMenu.add(newGameMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(addPlayerMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(startGameMenuItem);
    gameMenu.addSeparator();
    gameMenu.add(exitMenuItem);

    add(gameMenu);

  }

  /**
   * Add a listener to the menu.
   *
   * @param listener the listener
   */
  public void addListener(Features listener) {
    newGameMenuItem.addActionListener(new NewGameListener(listener));

    exitMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    startGameMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.startGame();
      }
    });

    addPlayerMenuItem.addActionListener(new AddPlayerListener(listener));
  }
}
