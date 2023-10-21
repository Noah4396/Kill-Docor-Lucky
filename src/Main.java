import controller.GamingConsoleController;
import model.GamingModel;

import java.io.InputStreamReader;

public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
   * @param args the arguments
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    new GamingConsoleController(input, output).playGame(new GamingModel("res/mansion.txt", 10));
  }
}
