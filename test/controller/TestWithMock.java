package controller;

import model.GamingModel;
import model.MockModel;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import static org.junit.Assert.assertEquals;
public class TestWithMock {
  private Model gamingModel;
  private StringBuilder log;
  @Before
  public void setUp() {
    log = new StringBuilder();
    gamingModel = new MockModel("res/mansion.txt", 5, log, "123");
  }

  @Test
  public void testAddPlayerWithMock(){
    StringReader input = new StringReader("1 p1 1 0 2 p2 1 1 1 p3 1 1 3 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals("Input: Character name: p1, Current room: Armory, items: [], player type: human 0\n"
        + "Input: Character name: p2, Current room: Billiard Room, items: [], player type: human 1\n"
        + "Input: Character name: p3, Current room: Billiard Room, items: [], player type: human 1", log.toString());
  }
}
