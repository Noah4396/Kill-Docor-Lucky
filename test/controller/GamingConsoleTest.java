package controller;

import org.junit.Before;
import org.junit.Test;
import world.PlayerCharacter;

import static org.junit.Assert.*;

public class GamingConsoleTest {

  private GamingConsole gamingConsole;
  PlayerCharacter p1;
  PlayerCharacter p2;
  PlayerCharacter p3;
  @Before
  public void setUp() throws Exception {
    gamingConsole = new GamingConsole("res/mansion.txt");
    p1 = new PlayerCharacter("p1", 0, 2);
    p2 = new PlayerCharacter("p2", 1, 4);
    p3 = new PlayerCharacter("p3", 2, 1);
  }
  @Test
  public void addPlayer() {
    gamingConsole.addPlayer(p1, 0);
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[TargetCharacter{roomIndex=0, roomName = Armory, index=-1, health=50, name='Doctor Lucky'}, Character name: p1, Current room: Armory, items: []]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]", gamingConsole.displayRoom(0));
    assertEquals("[Character name: p1, Current room: Armory, items: []]", gamingConsole.displayers());

  }

  @Test
  public void pickUpItem() {
    gamingConsole.addPlayer(p1, 0);
    gamingConsole.pickUpItem(p1, 1);
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[], \n"
        + "characters=[TargetCharacter{roomIndex=0, roomName = Armory, index=-1, health=50, name='Doctor Lucky'}, Character name: p1, Current room: Armory, items: [Revolver]]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]", gamingConsole.displayRoom(0));
    assertEquals("[Character name: p1, Current room: Armory, items: [Revolver]]", gamingConsole.displayers());
  }

  @Test(expected = IllegalStateException.class)
  public void invalidPlayerPick(){
    gamingConsole.pickUpItem(p2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pickupInvalidItem(){
    gamingConsole.addPlayer(p1, 0);
    gamingConsole.pickUpItem(p1, 10);
  }

  @Test
  public void moveToNeighbour() {
    gamingConsole.addPlayer(p1, 0);
    gamingConsole.moveToNeighbour(p1, 0, 0);
    assertEquals("[Character name: p1, Current room: Dining Hall, items: []]", gamingConsole.displayers());
    assertEquals(3, p1.getRoom().getIndex());
    assertEquals("SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[TargetCharacter{roomIndex=0, roomName = Armory, index=-1, health=50, name='Doctor Lucky'}]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]", gamingConsole.displayRoom(0));
    assertEquals("SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Dining Hall, items: []]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]", gamingConsole.displayRoom(3));
  }


  @Test
  public void lookAround() {
    gamingConsole.addPlayer(p1, 0);
    gamingConsole.addPlayer(p2, 2);
    gamingConsole.addPlayer(p3, 15);
    assertEquals("Character name: p2, Current room: Carriage House, items: []\n"
        + "Visible Rooms: Visible room of Carriage House: [Winter Garden, Piazza, Hedge Maze, Green House, ]\n"
        + "\n" + "Character name: p3, Current room: Piazza, items: []\n"
        + "Visible Rooms: Visible room of Piazza: [Foyer, Drawing Room, Dining Hall, Tennessee Room, Lilac Room, Master Suite, Hedge Maze, Green House, Winter Garden, Carriage House, ]\n"
        + "\n", gamingConsole.lookAround(p1));
  }
}