package driver;

import controller.GamingConsoleController;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.GamingModel;

/**
 * The driver of milestone2.
 */
public class Main {
  /**
   * Run a Kill doctor lucky game interactively on the console.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    String path;
    Scanner scan = new Scanner(System.in);
    int turnNumber;
    if (args.length == 0) {
      System.out.println("Please enter the path of the world file:");
      path = scan.next();
      System.out.println("Please enter the turn number:");
      turnNumber = scan.nextInt();
    } else {
      path = args[0];
      turnNumber = Integer.parseInt(args[1]);
    }
    new GamingConsoleController(input, output).playGame(new GamingModel(path, turnNumber));
  }
}
