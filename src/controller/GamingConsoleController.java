package controller;

import world.PlayerCharacter;

import java.util.ArrayList;
import java.util.Scanner;

public class GamingConsoleController implements Controller{
  private final Appendable out;
  private final Scanner scan;
  private ArrayList<PlayerCharacter> players;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public GamingConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }



  @Override
  public void playGame(GamingModel m) {

  }
}
