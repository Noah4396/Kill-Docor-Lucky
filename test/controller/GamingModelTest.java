package controller;

import static org.junit.Assert.assertEquals;

import model.GamingModel;
import org.junit.Before;
import org.junit.Test;
import world.PlayerCharacter;

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
    gamingModel = new GamingModel("res/mansion.txt", 100);
    p1 = new PlayerCharacter("p1", 0, 2);
    p2 = new PlayerCharacter("p2", 1, 4);
    p3 = new PlayerCharacter("p3", 2, 1);
    p4 = new PlayerCharacter("p3", 4, 0);
  }

  @Test
  public void addPlayer() {
    gamingModel.addPlayer(p1, 0);
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
            + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
            + "items in the room =[Revolver], \n"
            + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: "
            + "p1, Current room: Armory, items: [], player type: human]}, \n"
            + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
            + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, "
            + "Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]",
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
    gamingModel.addPlayer(p1, 0);
    gamingModel.pickUpItem(p1, 0);
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
            + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
            + "items in the room =[], \n"
            + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, "
            + "Current room: Armory, items: [Revolver], player type: human]}, \n"
            + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
            + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, "
            + "Tennessee "
            + "Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]",
        gamingModel.displayRoom(0));
    assertEquals("Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: human\n",
        gamingModel.displayers());
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
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
            + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
            + "items in the room =[Revolver], \n"
            + "characters=[Target name: Doctor Lucky, Current room: Armory]}, \n"
            + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
            + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, "
            + "Tennessee "
            + "Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]",
        gamingModel.displayRoom(0));
    assertEquals("SpecifiedRoom{name='Dining Hall', index=3, \n"
            + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
            + "items in the room =[], \n"
            + "characters=[Character name: p1, Current room: Dining Hall, items: [], "
            + "player type: human]}, \n"
            + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, "
            + "Drawing Room, ][Parlor, Kitchen, ], \n"
            + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room"
            + ", Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]",
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
    gamingModel.addPlayer(p2, 2);
    gamingModel.addPlayer(p3, 15);
    assertEquals(
        "p1 looks around!\n" + "Information of the current room:\n" + "Room name:Armory\n"
            + "Characters: Doctor Lucky, p1, \n" + "Items: Revolver, \n"
            + "Visible rooms: Dining Hall, Billiard Room, Drawing Room, \n" + "\n"
            + "Visible room of Armory:\n" + "Room name:Dining Hall\n" + "Characters: \n"
            + "Items: \n"
            + "Visible rooms: Tennessee Room, Billiard Room, Trophy Room, Armory, Wine Cellar, "
            + "Drawing Room, Parlor, Kitchen, \n"
            + "\n" + "Room name:Billiard Room\n" + "Characters: \n" + "Items: Billiard Cue, \n"
            + "Visible rooms: Trophy Room, Armory, Dining Hall, \n" + "\n"
            + "Room name:Drawing Room\n" + "Characters: \n" + "Items: Letter Opener, \n"
            + "Visible rooms: Dining Hall, Armory, Foyer, Wine Cellar, \n" + "\n",
        gamingModel.lookAround(p1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void lookAroundInvalidPlayer() {
    gamingModel.lookAround(p1);
    gamingModel.lookAround(null);
  }
}