package controller;

import model.GamingModel;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class GamingConsoleControllerTest {
  private GamingModel gamingModel;
  private StringBuffer expectOutput;
  @Before
  public void setUp()  {
    gamingModel = new GamingModel("res/mansion.txt", 100);
    expectOutput = new StringBuffer();
    expectOutput.append("Welcome to the kill doctor lucky game\n");
    expectOutput.append("The rooms are initialized as:\n");
    expectOutput.append(gamingModel.displayRooms());
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable(){
    StringReader input = new StringReader("2 p1 1 0 2 p2 1 1 2 p3 2 2 2 p4 1 4 3");
    Appendable gamelog = new FailingAppendable();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
  }

  @Test
  public void testInvalidCommand(){
    StringReader input = new StringReader("wofj fpslf lgwkg");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Invalid option, please enter again\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Invalid option, please enter again\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Invalid option, please enter again\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }
}