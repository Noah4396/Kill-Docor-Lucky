package controller;

import static org.junit.Assert.assertEquals;

import model.GamingModel;
import model.MockModel;
import model.Model;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test with mock.
 */
public class TestWithMock {
  private Model gamingModel;
  private StringBuilder log;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    gamingModel = new MockModel("res/mansion.txt", 5, log, "12341234");
  }

  @Test
  public void testAddPlayerWithMock() {
    StringReader input = new StringReader("1 p1 1 0 2 p2 1 1 1 p3 1 1 3 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals(
        "Input: Character name: p1, Current room: Armory, items:"
            + " [], player type: human 0\n"
            + "Input: Character name: p2, Current room: Billiard Room, items: "
            + "[], player type: human 1\n"
            + "Input: Character name: p3, Current room: Billiard Room, items: "
            + "[], player type: human 1\n",
        log.toString());
  }

  @Test
  public void testComputerCommandWithMock() {
    StringReader input = new StringReader("2 p1 1 0 2 p2 1 1 2 p3 2 2 2 p4 1 4 3");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals(
        "Input: Character name: p1, Current room: Armory, items: [], "
            + "player type: human 0\n"
            + "Input: Character name: p2, Current room: Billiard Room, items: ["
            + "], player type: human 1\n"
            + "Input: Character name: p3, Current room: Carriage House, items: "
            + "[], player type: human 2\n"
            + "Input: Character name: p4, Current room: Drawing Room, items: [],"
            + " player type: human 4\n"
            + "Input: Character name: p1, Current room: Armory, items: [Revolver],"
            + " player type: computer\n"
            + "Input: Character name: p2, Current room: Billiard Room, items: [Bill"
            + "iard Cue], player type: computer\n"
            + "Input: Character name: p3, Current room: Winter Garden, items: [], "
            + "player type: computer\n"
            + "Input: Character name: p4, Current room: Wine Cellar, items: [], pl"
            + "ayer type: computer\n"
            + "Input: Character name: p1, Current room: Armory, items: [Revolver],"
            + " player type: computer\n"
            + "Input: Character name: p1, Current room: Armory, items: [Revolver], "
            + "player type: computer\n",
        log.toString());
  }

  @Test
  public void testMoveToNeighbourWithMock() {
    StringReader input = new StringReader("1 p1 1 3 3 1 0 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals(
        "Input: Character name: p1, Current room: Dining Hall, items: [],"
            + " player type: human 3\n"
            + "Input: Character name: p1, Current room: Tennessee Room, items: [],"
            + " player type: human 0 0\n",
        log.toString());
  }

  @Test
  public void testPickItemWithMock() {
    StringReader input = new StringReader("1 p1 1 0 3 2 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals(
        "Input: Character name: p1, Current room: Armory, items: [], "
            + "player type: human 0\n"
            + "Input: Character name: p1, Current room: Armory, items: [Revolver],"
            + " player type: human 0\n",
        log.toString());
  }

  @Test
  public void testLookAroundWithMock() {
    StringReader input = new StringReader("1 p1 1 0 1 p2 2 1 2 p3 3 10 3 3 3 3 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    assertEquals(
        "Input: Character name: p1, Current room: Armory, items: [], "
            + "player type: human 0\n"
            + "Input: Character name: p2, Current room: Billiard Room, items: "
            + "[], player type: human 1\n"
            + "Input: Character name: p3, Current room: Library, items: [], pl"
            + "ayer type: human 10\n"
            + "Input: Character name: p1, Current room: Armory, items: [], play"
            + "er type: human\n"
            + "Input: Character name: p2, Current room: Billiard Room, items: ["
            + "], player type: human\n"
            + "Input: Character name: p3, Current room: Library, items: [], play"
            + "er type: computer\n"
            + "Input: Character name: p3, Current room: Library, items: [], p"
            + "layer type: computer\n"
            + "Input: Character name: p1, Current room: Armory, items: [], pl"
            + "ayer type: human\n",
        log.toString());
    assertEquals("Welcome to the kill doctor lucky game\n" + "The roo"
        + "ms are initialized as:\n"
        + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Target name: Doctor Lucky, Current room: Armory]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Librar"
        + "y, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Billiard Room', index=1, \n"
        + "leftCorner=21, rightCorner=28, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Billiard Cue], \n" + "characters=[]}, \n"
        + "neighbours = [Trophy Room, ][][Armory, ][Dining Hall, ], \n"
        + "visibleRooms = [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "\n" + "SpecifiedRoom{name='Carriage House', index=2, \n"
        + "leftCorner=0, rightCorner=5, upperCorner=28, lowerCorner=35, \n"
        + "items in the room =[Chain Saw, Big Red Hammer], \n" + "characters=[]}, \n"
        + "neighbours = [][Winter Garden, ][][], \n"
        + "visibleRooms = [Winter Garden, Piazza, Hedge Maze, Green House, ]\n" + "\n"
        + "SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Arm"
        + "ory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiar"
        + "d Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]\n"
        + "\n" + "SpecifiedRoom{name='Drawing Room', index=4, \n"
        + "leftCorner=13, rightCorner=18, upperCorner=22, lowerCorner=25, \n"
        + "items in the room =[Letter Opener], \n" + "characters=[]}, \n"
        + "neighbours = [Dining Hall, ][Armory, ][Foyer, ][Wine Cellar, ], \n"
        + "visibleRooms = [Dining Hall, Tennessee Room, Lilac Room, Master S"
        + "uite, Armory, Foyer, Piazza, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Foyer', index=5, \n"
        + "leftCorner=13, rightCorner=18, upperCorner=26, lowerCorner=27, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [Drawing Room, ][][Piazza, ][], \n"
        + "visibleRooms = [Drawing Room, Dining Hall, Tennessee Room, Lilac Roo"
        + "m, Master Suite, Piazza, ]\n"
        + "\n" + "SpecifiedRoom{name='Green House', index=6, \n"
        + "leftCorner=26, rightCorner=29, upperCorner=28, lowerCorner=35, \n"
        + "items in the room =[Trowel, Pinking Shears], \n" + "characters=[]}, \n"
        + "neighbours = [][][][Hedge Maze, ], \n"
        + "visibleRooms = [Hedge Maze, Piazza, Winter Garden, Carriage House, ]\n" + "\n"
        + "SpecifiedRoom{name='Hedge Maze', index=7, \n"
        + "leftCorner=20, rightCorner=25, upperCorner=30, lowerCorner=35, \n"
        + "items in the room =[Loud Noise], \n" + "characters=[]}, \n"
        + "neighbours = [][Green House, ][][Piazza, ], \n"
        + "visibleRooms = [Green House, Piazza, Winter Garden, Carriage House, ]\n" + "\n"
        + "SpecifiedRoom{name='Kitchen', index=8, \n"
        + "leftCorner=3, rightCorner=10, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Crepe Pan, Sharp Knife], \n" + "characters=[]}, \n"
        + "neighbours = [Parlor, ][Dining Hall, ][Wine Cellar, ][], \n"
        + "visibleRooms = [Parlor, Servants' Quarters, Lancaster Room, Dining Ha"
        + "ll, Billiard Room, Trophy Room, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Lancaster Room', index=9, \n"
        + "leftCorner=3, rightCorner=8, upperCorner=0, lowerCorner=5, \n"
        + "items in the room =[Silken Cord], \n" + "characters=[]}, \n"
        + "neighbours = [][Lilac Room, ][Servants' Quarters, ][], \n"
        + "visibleRooms = [Lilac Room, Master Suite, Library, Nursery, Servants' Qu"
        + "arters, Parlor, Kitchen, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Library', index=10, \n"
        + "leftCorner=23, rightCorner=28, upperCorner=4, lowerCorner=9, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [Nursery, ][][Trophy Room, ][Master Suite, ], \n"
        + "visibleRooms = [Nursery, Trophy Room, Billiard Room, Armory, Master S"
        + "uite, Lilac Room, Lancaster Room, Servants' Quarters, ]\n"
        + "\n" + "SpecifiedRoom{name='Lilac Room', index=11, \n"
        + "leftCorner=9, rightCorner=14, upperCorner=2, lowerCorner=7, \n"
        + "items in the room =[Tight Hat], \n" + "characters=[]}, \n"
        + "neighbours = [][Master Suite, ][Tennessee Room, ][Lancaster Room, Servan"
        + "ts' Quarters, ], \n"
        + "visibleRooms = [Master Suite, Library, Nursery, Tennessee Room, Dining H"
        + "all, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Lancaster Room, "
        + "Servants' Quarters, ]\n"
        + "\n" + "SpecifiedRoom{name='Master Suite', index=12, \n"
        + "leftCorner=15, rightCorner=22, upperCorner=2, lowerCorner=7, \n"
        + "items in the room =[Shoe Horn], \n" + "characters=[]}, \n"
        + "neighbours = [][Library, Nursery, ][Tennessee Room, ][Lilac Room, ], \n"
        + "visibleRooms = [Library, Nursery, Tennessee Room, Dining Hall, Armory, Wine C"
        + "ellar, Drawing Room, Foyer, Piazza, Lilac Room, Lancaster Room, Servants' Quarters, ]\n"
        + "\n" + "SpecifiedRoom{name='Nursery', index=13, \n"
        + "leftCorner=23, rightCorner=28, upperCorner=0, lowerCorner=3, \n"
        + "items in the room =[Bad Cream], \n" + "characters=[]}, \n"
        + "neighbours = [][][Library, ][Master Suite, ], \n"
        + "visibleRooms = [Library, Trophy Room, Billiard Room, Armory, Master Suite,"
        + " Lilac Room, Lancaster Room, Servants' Quarters, ]\n"
        + "\n" + "SpecifiedRoom{name='Parlor', index=14, \n"
        + "leftCorner=5, rightCorner=10, upperCorner=10, lowerCorner=15, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [Servants' Quarters, ][Dining Hall, Tennessee Room, ][Kitchen, ][], \n"
        + "visibleRooms = [Servants' Quarters, Lancaster Room, Dining Hall, Tennessee R"
        + "oom, Trophy Room, Billiard Room, Trophy Room, Kitchen, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Piazza', index=15, \n"
        + "leftCorner=12, rightCorner=19, upperCorner=28, lowerCorner=35, \n"
        + "items in the room =[Civil War Cannon], \n" + "characters=[]}, \n"
        + "neighbours = [Foyer, ][Hedge Maze, ][][Winter Garden, ], \n"
        + "visibleRooms = [Foyer, Drawing Room, Dining Hall, Tennessee Room, Lilac"
        + " Room, Master Suite, Hedge Maze, Green House, Winter Garden, Carriage House, ]\n"
        + "\n" + "SpecifiedRoom{name='Servants' Quarters', index=16, \n"
        + "leftCorner=3, rightCorner=8, upperCorner=6, lowerCorner=9, \n"
        + "items in the room =[Broom Stick], \n" + "characters=[]}, \n"
        + "neighbours = [Lancaster Room, ][Lilac Room, ][Parlor, ][], \n"
        + "visibleRooms = [Lancaster Room, Lilac Room, Master Suite, Library, Nursery, P"
        + "arlor, Kitchen, Wine Cellar, ]\n"
        + "\n" + "SpecifiedRoom{name='Tennessee Room', index=17, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=8, lowerCorner=11, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [Lilac Room, Master Suite, ][Trophy Room, ][Dining Hall, ][Parlor, ], \n"
        + "visibleRooms = [Lilac Room, Master Suite, Trophy Room, Dining Hall, Armor"
        + "y, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, ]\n"
        + "\n" + "SpecifiedRoom{name='Trophy Room', index=18, \n"
        + "leftCorner=21, rightCorner=26, upperCorner=10, lowerCorner=15, \n"
        + "items in the room =[Duck Decoy, Monkey Hand], \n" + "characters=[]}, \n"
        + "neighbours = [Library, ][][Billiard Room, ][Dining Hall, Tennessee Room, ], \n"
        + "visibleRooms = [Library, Nursery, Billiard Room, Armory, Dining Hall, Tennessee "
        + "Room, Parlor, Parlor, Kitchen, ]\n"
        + "\n" + "SpecifiedRoom{name='Wine Cellar', index=19, \n"
        + "leftCorner=5, rightCorner=12, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Rat Poison, Piece of Rope], \n" + "characters=[]}, \n"
        + "neighbours = [Dining Hall, Kitchen, ][Drawing Room, ][][], \n"
        + "visibleRooms = [Dining Hall, Kitchen, Parlor, Servants' Quarters, Lancaster Ro"
        + "om, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Armory, ]\n"
        + "\n" + "SpecifiedRoom{name='Winter Garden', index=20, \n"
        + "leftCorner=6, rightCorner=11, upperCorner=30, lowerCorner=35, \n"
        + "items in the room =[], \n" + "characters=[]}, \n"
        + "neighbours = [][Piazza, ][][Carriage House, ], \n"
        + "visibleRooms = [Piazza, Hedge Maze, Green House, Carriage House, ]\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start th"
        + "e game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start th"
        + "e game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start t"
        + "he game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start th"
        + "e game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1"
        + ", Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Te"
        + "nnessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "12341234Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p2\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Billiard Room', index=1, \n"
        + "leftCorner=21, rightCorner=28, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Billiard Cue], \n"
        + "characters=[Character name: p2, Current room: Billiard Room, items: [], pla"
        + "yer type: human, Target name: Doctor Lucky, Current room: Billiard Room]}, \n"
        + "neighbours = [Trophy Room, ][][Armory, ][Dining Hall, ], \n"
        + "visibleRooms = [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "12341234Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Carriage House\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "---------------------------------\n" + "Computer p3 Look around!\n"
        + "---------------------------------\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Dining Hall\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Now we are in the 4 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery"
        + ", Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "12341234Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Drawing Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Now we are in the 5 turn\n" + "The player in current turn is: p2\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Billiard Room', index=1, \n"
        + "leftCorner=21, rightCorner=28, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Billiard Cue], \n"
        + "characters=[Character name: p2, Current room: Billiard Room, items: [], p"
        + "layer type: human]}, \n"
        + "neighbours = [Trophy Room, ][][Armory, ][Dining Hall, ], \n"
        + "visibleRooms = [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n", gamelog.toString());
  }
}
