package controller;

import static org.junit.Assert.assertEquals;

import model.MockModel;
import model.Model;
import org.junit.Before;
import org.junit.Test;
import view.GameBoardPanel;
import view.MockView;
import view.View;

public class ViewControllerMockTest {
  private Model mockModel;
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  private View mockView;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    modelLog = new StringBuilder();
    mockModel = new MockModel("res/mansion.txt", 5, modelLog, "12341234");
    viewLog = new StringBuilder();
    mockView = new MockView(viewLog, "12341234");
  }

  @Test
  public void testPlayGame(){
    Controller controller = new ViewController(mockView, mockModel);
    controller.playGame(mockModel);
    assert (viewLog.toString().contains("makeVisible\n"));
  }

  @Test
  public void testStartNewGame(){
    ViewController controller = new ViewController(mockView, mockModel);
    controller.startNewGame("res/mansion.txt", 5);
    StringBuffer expected = new StringBuffer();
    expected.append("setModel\n");
    expected.append("paintLayout\n");
    expected.append("refresh\n");
    assertEquals(expected.toString(), viewLog.toString());
    assertEquals("move, input: Doctor Lucky Armory\n", modelLog.toString());
  }

  @Test
  public void testStartGame(){
    ViewController controller = new ViewController(mockView, mockModel);
    controller.startGame();
    StringBuffer expectedViewLog = new StringBuffer();
    expectedViewLog.append("setPanelFeatures, input:" + controller.hashCode() + "\n" + "refresh\n");
    assertEquals(expectedViewLog.toString(), viewLog.toString());

    StringBuffer expectedModelLog = new StringBuffer();
    expectedModelLog.append("move, input: Doctor Lucky Armory\n" + "getTurn\n"
        + "move, input: Doctor Lucky Billiard Room\n" + "isGameStart\n"
        + "setGameStart, input: true\n");
    assertEquals(expectedModelLog.toString(), modelLog.toString());
  }

  @Test
  public void testAddPlayer(){
    ViewController controller = new ViewController(mockView, mockModel);
    controller.addPlayer("test", 5, 1, true);
    StringBuffer expectedViewLog = new StringBuffer();
    expectedViewLog.append("addPlayer, input:Character name: test\n" + "capacity: 5\n"
        + "Current room: Billiard Room\n" + "index: 0\n" + "items: []\n" + "player type: computer\n"
        + "refresh\n");
    assertEquals(expectedViewLog.toString(), viewLog.toString());

    StringBuffer expectedModelLog = new StringBuffer();
    expectedModelLog.append("move, input: Doctor Lucky Armory\n" + "isGameStart\n" + "getPlayers\n"
        + "getPlayers\n" + "move, input: test Billiard Room\n" + "Input: Character name: test\n"
        + "capacity: 5\n" + "Current room: Billiard Room\n" + "index: 0\n" + "items: []\n"
        + "player type: human 1\n");
    assertEquals(expectedModelLog.toString(), modelLog.toString());
  }

  @Test
  public void testMovePlayer() {
    ViewController controller = new ViewController(mockView, mockModel);
    controller.movePlayer(mockModel.getRooms().get(0));
    StringBuffer expectedViewLog = new StringBuffer();
    expectedViewLog.append("refresh\n");
    assertEquals(expectedViewLog.toString(), viewLog.toString());

    StringBuffer expectedModelLog = new StringBuffer();
    expectedModelLog.append("move, input: Doctor Lucky Armory\n" + "getTurn\n"
        + "move, input: Doctor Lucky Billiard Room\n" + "move, input: Doctor Lucky Armory\n"
        + "passTurn\n");
    assertEquals(expectedModelLog.toString(), modelLog.toString());
  }

  @Test
  public void testExecuteCommand(){
    ViewController controller = new ViewController(mockView, mockModel);
    controller.executeCommand(1, new GameBoardPanel(mockModel));
    StringBuffer expectedViewLog = new StringBuffer();
    expectedViewLog.append("");
    assertEquals(expectedViewLog.toString(), viewLog.toString());

    StringBuffer expectedModelLog = new StringBuffer();
    expectedModelLog.append("move, input: Doctor Lucky Armory\n");
    assertEquals(expectedModelLog.toString(), modelLog.toString());
  }
}
