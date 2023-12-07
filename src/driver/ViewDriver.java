package driver;

import controller.ViewController;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.GamingModel;
import view.GamingView;

/**
 * The driver for the view.
 */
public class ViewDriver {
  /**
   * Run a Kill doctor lucky game interactively on the console.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    String path = "res/mansion.txt";
    int turnNumber = 10;
    Scanner scan = new Scanner(System.in);
    GamingModel model;
    if (args.length == 0) {
      model = null;
    } else {
      path = args[0];
      turnNumber = Integer.parseInt(args[1]);
      model = new GamingModel(path, turnNumber);
    }
    GamingView view = new GamingView(model);
    ViewController controller = new ViewController(view, model);
    view.setMenuFeatures(controller);
    view.makeVisible();
  }
}
