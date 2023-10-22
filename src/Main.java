import controller.GamingConsoleController;
import model.GamingModel;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
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
