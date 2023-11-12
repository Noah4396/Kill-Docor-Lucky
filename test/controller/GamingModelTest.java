package controller;

import model.GamingModel;
import org.junit.Before;
import org.junit.Test;
import world.PlayerCharacter;

import static org.junit.Assert.*;

/**
 * Test the game model.
 */
public class GamingModelTest {

  private GamingModel gamingModel;
  private PlayerCharacter p1;
  private PlayerCharacter p2;
  private PlayerCharacter p3;
  private PlayerCharacter p4;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    gamingModel = new GamingModel("res/mansion.txt", 10);
    p1 = new PlayerCharacter("p1", 0, 2);
    p2 = new PlayerCharacter("p2", 1, 4);
    p3 = new PlayerCharacter("p3", 2, 1);
    p4 = new PlayerCharacter("p3", 4, 0);
  }

  @Test
  public void addPlayer() {
    gamingModel.addPlayer(p1, 0);
    assertEquals("Room name:Armory\n" + "Characters: Doctor Lucky, Fortune the Cat, p1, \n"
            + "Items: Revolver, \n" + "Neighbours: Dining Hall, Billiard Room, Drawing Room, ",
        gamingModel.displayRoom(0));
    assertEquals("Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: human\n",
        gamingModel.displayers());

  }

  @Test(expected = IllegalArgumentException.class)
  public void addInvalidPlayer() {
    gamingModel.addPlayer(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPlayerInvalidIndex() {
    gamingModel.addPlayer(p1, 100);
  }


  @Test
  public void pickUpItem() {
    gamingModel.addPlayer(p1, 1);
    gamingModel.pickUpItem(p1, 0);
    assertEquals("Room name:Billiard Room\n" + "Characters: p1, \n" + "Items: \n"
            + "Neighbours: Trophy Room, Armory, Dining Hall, ",
        gamingModel.displayRoom(1));
    assertEquals("Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Billiard Room, items: [Billiard Cue], "
        + "player type: human\n", gamingModel.displayers());

    gamingModel.moveToNeighbour(p1, 2, 0);
    gamingModel.pickUpItem(p1, 0);
    assertEquals("Room name:Armory\n" + "Characters: Fortune the Cat, Doctor Lucky, p1, \n"
            + "Items: \n" + "Neighbours: Dining Hall, Billiard Room, Drawing Room, ",
        gamingModel.displayRoom(0));
    assertEquals("Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [Revolver, Billiard Cue],"
        + " player type: human", gamingModel.displayers());
  }

  @Test(expected = IllegalStateException.class)
  public void invalidPlayerPick() {
    gamingModel.pickUpItem(p2, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void morethanCapacity() {
    gamingModel.addPlayer(p4, 0);
    gamingModel.pickUpItem(p4, 0);
  }
  @Test(expected = IllegalArgumentException.class)
  public void pickupInvalidItem() {
    gamingModel.addPlayer(p1, 0);
    gamingModel.pickUpItem(p1, 10);
  }

  @Test
  public void moveToNeighbour() {
    gamingModel.addPlayer(p1, 0);
    gamingModel.moveToNeighbour(p1, 0, 0);
    assertEquals("Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n",
        gamingModel.displayers());
    assertEquals(3, p1.getRoom().getIndex());
    assertEquals("Room name:Armory\n" + "Characters: Doctor Lucky, Fortune the Cat, \n"
            + "Items: Revolver, \n" + "Neighbours: Dining Hall, Billiard Room, Drawing Room, ",
        gamingModel.displayRoom(0));
    assertEquals("Room name:Dining Hall\n" + "Characters: p1, \n" + "Items: \n"
            + "Neighbours: Tennessee Room, Billiard Room, Trophy Room, Armory, Wine Cellar, "
            + "Drawing Room, Parlor, Kitchen, ",
        gamingModel.displayRoom(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveToInvalidNeighbour() {
    gamingModel.addPlayer(p1, 0);
    gamingModel.moveToNeighbour(p1, 1, 0);
    gamingModel.moveToNeighbour(p1, 100, 0);
    gamingModel.moveToNeighbour(p1, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveInvalidPlayer() {
    gamingModel.moveToNeighbour(p1, 0, 0);
    gamingModel.moveToNeighbour(null, 0, 0);
  }

  @Test
  public void lookAround() {
    gamingModel.addPlayer(p1, 0);
    gamingModel.addPlayer(p2, 1);
    gamingModel.addPlayer(p3, 15);
    assertEquals(
        "p1 looks around!\n" + "Information of the current room:\n" + "Room name:Armory\n"
            + "Characters: Doctor Lucky, Fortune the Cat, p1, \n" + "Items: Revolver, \n"
            + "Neighbours: Dining Hall, Billiard Room, Drawing Room, \n" + "\n"
            + "Visible room of Armory:\n" + "Room name:Dining Hall\n" + "Characters: \n"
            + "Items: \n"
            + "Neighbours: Tennessee Room, Billiard Room, Trophy Room, Armory, Wine Cellar,"
            + " Drawing Room, Parlor, Kitchen, \n"
            + "\n" + "Room name:Billiard Room\n" + "Characters: p2, \n" + "Items: Billiard Cue, \n"
            + "Neighbours: Trophy Room, Armory, Dining Hall, \n" + "\n" + "Room name:Drawing Room\n"
            + "Characters: \n" + "Items: Letter Opener, \n"
            + "Neighbours: Dining Hall, Armory, Foyer, Wine Cellar, \n\n",
        gamingModel.lookAround(p1));
    assertEquals("p2 looks around!\n" + "Information of the current room:\n"
        + "Room name:Billiard Room\n" + "Characters: p2, \n" + "Items: Billiard Cue, \n"
        + "Neighbours: Trophy Room, Armory, Dining Hall, \n" + "\n"
        + "Visible room of Billiard Room:\n" + "Room name:Trophy Room\n" + "Characters: \n"
        + "Items: Duck Decoy, Monkey Hand, \n"
        + "Neighbours: Library, Billiard Room, Dining Hall, Tennessee Room, \n" + "\n"
        + "Room name:Dining Hall\n" + "Characters: \n" + "Items: \n"
        + "Neighbours: Tennessee Room, Billiard Room, Trophy Room, Armory, Wine Cellar, "
        + "Drawing Room, Parlor, Kitchen, \n"
        + "\n", gamingModel.lookAround(p2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void lookAroundInvalidPlayer() {
    gamingModel.lookAround(p1);
    gamingModel.lookAround(null);
  }

  @Test
  public void initializePet(){
    assertEquals("Room name:Armory\n" + "Characters: Doctor Lucky, Fortune the Cat, \n"
        + "Items: Revolver, \n" + "Neighbours: Dining Hall, Billiard Room, Drawing Room, ",
        gamingModel.displayRoom(0));
  }

  @Test
  public void isVisible(){
    gamingModel.addPlayer(p1, 0);
    gamingModel.addPlayer(p2, 1);
    assertTrue(gamingModel.isVisibleBy(p1, p2));
    assertFalse(gamingModel.isVisibleBy(p2, p1));
  }

  @Test
  public void petDepthFirstMove(){
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < 40; i++){
      sb.append(gamingModel.petInfo() + "\n");
      gamingModel.movePetDepthFirst();
    }
    assertEquals("Armory\n" + "Drawing Room\n" + "Wine Cellar\n" + "Kitchen\n" + "Parlor\n"
        + "Tennessee Room\n" + "Trophy Room\n" + "Library\n" + "Nursery\n" + "Master Suite\n"
        + "Lilac Room\n" + "Lancaster Room\n" + "Servants' Quarters\n" + "Foyer\n" + "Piazza\n"
        + "Winter Garden\n" + "Carriage House\n" + "Hedge Maze\n" + "Green House\n"
        + "Billiard Room\n" + "Dining Hall\n" + "Armory\n" + "Drawing Room\n" + "Wine Cellar\n"
        + "Kitchen\n" + "Parlor\n" + "Tennessee Room\n" + "Trophy Room\n" + "Library\n"
        + "Nursery\n" + "Master Suite\n" + "Lilac Room\n" + "Lancaster Room\n"
        + "Servants' Quarters\n" + "Foyer\n" + "Piazza\n" + "Winter Garden\n" + "Carriage House\n"
        + "Hedge Maze\n" + "Green House\n", sb.toString());
  }

  @Test
  public void movePet(){
    StringBuffer sb = new StringBuffer();
    sb.append(gamingModel.petInfo() + "\n");
    gamingModel.addPlayer(p1, 0);
    gamingModel.movePet(p1, 0, 0);
    sb.append(gamingModel.petInfo() + "\n");
    assertEquals("Armory\n" + "Dining Hall\n", sb.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void movePetNotInTheSameRoom(){
    gamingModel.addPlayer(p1, 1);
    gamingModel.movePet(p1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void movePetInvalidDirection(){
    gamingModel.addPlayer(p1, 0);
    gamingModel.movePet(p1, 100, 0);
  }

  @Test
  public void attempt(){
    gamingModel.addPlayer(p1, 0);
    gamingModel.addPlayer(p2, 1);
    gamingModel.pickUpItem(p1, 0);
    assertEquals(50, gamingModel.getDoctorLucky().getHealth());
    gamingModel.attempt(p1, 0);
    assertEquals(47, gamingModel.getDoctorLucky().getHealth());
    gamingModel.attempt(p1, 100);
    assertEquals(46, gamingModel.getDoctorLucky().getHealth());

    gamingModel.movePetDepthFirst();
    gamingModel.moveToNeighbour(p2, 0, 0);
    gamingModel.attempt(p1, 0);
    assertEquals(45, gamingModel.getDoctorLucky().getHealth());
  }

  @Test
  public void failAttempt(){
    gamingModel.addPlayer(p1, 0);
    gamingModel.moveTarget();
    gamingModel.addPlayer(p2, 1);
    gamingModel.attempt(p2, 0);
    assertEquals(50, gamingModel.getDoctorLucky().getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAttempt(){
    gamingModel.addPlayer(p1, 0);
    gamingModel.addPlayer(p2, 1);
    gamingModel.attempt(p2, 0);
  }

  @Test
  public void testComputer(){
    p1.setAsComputer();
    p2.setAsComputer();
    StringBuffer sb = new StringBuffer();
    gamingModel.addPlayer(p1, 0);
    gamingModel.addPlayer(p2, 1);
    while(!gamingModel.isGameOver()){
      gamingModel.computerCommand(p1, sb);
      gamingModel.passTurn();
      gamingModel.computerCommand(p2, sb);
      gamingModel.passTurn();
    }
    assertEquals("Computer p1 attempts to kill the target!\n"
        + "Computer p2 attempts to kill the target!\n" + "Computer p1 pick up an item!\n"
        + "Computer p2 Look around!\n" + "Computer p1 Look around!\n"
        + "Computer p2 pick up an item!\n" + "Computer p1 Look around!\n"
        + "Computer p2 moves to the neighbour!\n" + "Computer p1 Look around!\n"
        + "Computer p2 moves to the neighbour!\n", sb.toString());
  }
}