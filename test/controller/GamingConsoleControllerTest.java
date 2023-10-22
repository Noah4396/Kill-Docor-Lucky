package controller;

import model.GamingModel;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class GamingConsoleControllerTest {
  private Model gamingModel;
  private StringBuffer expectOutput;

  @Before
  public void setUp() {
    gamingModel = new GamingModel("res/mansion.txt", 5);
    expectOutput = new StringBuffer();
    expectOutput.append("Welcome to the kill doctor lucky game\n");
    expectOutput.append("The rooms are initialized as:\n");
    expectOutput.append(gamingModel.displayRooms());
  }

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    StringReader input = new StringReader("2 p1 1 0 2 p2 1 1 2 p3 2 2 2 p4 1 4 3");
    Appendable gamelog = new FailingAppendable();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
  }

  @Test
  public void testInvalidCommand() {
    StringReader input = new StringReader("wofj 5 3 4");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append(
        "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Invalid option, please enter again\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Invalid option, please enter again\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "There are no player in the game, please create at least one player\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Ending game....\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testAddPlayer() {
    StringReader input = new StringReader("1 p1 1 0 2 p2 1 1 1 p3 1 1 3 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append(
        "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: human\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: human\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Billiard Room, items: [], player type: human\n"
            + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Game start! The players are listed as follow: \n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: human\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Billiard Room, items: [], player type: human\n"
            + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
            + "The information of the room that the player is in:\n"
            + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
            + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
            + "items in the room =[Revolver], \n"
            + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
            + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
            + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
            + "---------------------------------\n"
            + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
            + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
            + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testAddInvalidPlayer() {
    StringReader input = new StringReader("1 p1 1 100 1 p1 1 1 2 p1 1 1 4");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append(
        "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Invalid room index or name duplicated!\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Billiard Room, items: [], player type: human\n\n");
    expectOutput.append(
        "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Invalid room index or name duplicated!\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Ending game....\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testComputerPlayer() {
    StringReader input = new StringReader("2 p1 1 0 2 p2 1 1 2 p3 2 2 2 p4 1 4 3");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append(
        "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: computer\n" + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Carriage House, items: [], player type: computer\n"
            + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
            + "Please enter the index of the room that the player is in:\n"
            + "Add player successfully, the current player information:\n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Carriage House, items: [], player type: computer\n"
            + "Character name: p4, Current room: Drawing Room, items: [], player type: computer\n"
            + "\n"
            + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
            + "Game start! The players are listed as follow: \n"
            + "Target name: Doctor Lucky, Current room: Armory\n"
            + "Character name: p1, Current room: Armory, items: [], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Carriage House, items: [], player type: computer\n"
            + "Character name: p4, Current room: Drawing Room, items: [], player type: computer\n"
            + "---------------------------------\n" + "Computer p1 pick up an item!\n"
            + "---------------------------------\n"
            + "Now the status of players are shown as follow:\n"
            + "Target name: Doctor Lucky, Current room: Billiard Room\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [], player type: computer\n"
            + "Character name: p3, Current room: Carriage House, items: [], player type: computer\n"
            + "Character name: p4, Current room: Drawing Room, items: [], player type: computer\n"
            + "\n" + "---------------------------------\n" + "Computer p2 pick up an item!\n"
            + "---------------------------------\n"
            + "Now the status of players are shown as follow:\n"
            + "Target name: Doctor Lucky, Current room: Carriage House\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [Billiard Cue], player type: computer\n"
            + "Character name: p3, Current room: Carriage House, items: [], player type: computer\n"
            + "Character name: p4, Current room: Drawing Room, items: [], player type: computer\n"
            + "\n" + "---------------------------------\n" + "Computer p3 moves to the neighbour!\n"
            + "---------------------------------\n"
            + "Now the status of players are shown as follow:\n"
            + "Target name: Doctor Lucky, Current room: Dining Hall\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [Billiard Cue], player type: computer\n"
            + "Character name: p3, Current room: Winter Garden, items: [], player type: computer\n"
            + "Character name: p4, Current room: Drawing Room, items: [], player type: computer\n"
            + "\n" + "---------------------------------\n" + "Computer p4 moves to the neighbour!\n"
            + "---------------------------------\n"
            + "Now the status of players are shown as follow:\n"
            + "Target name: Doctor Lucky, Current room: Drawing Room\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [Billiard Cue], player type: computer\n"
            + "Character name: p3, Current room: Winter Garden, items: [], player type: computer\n"
            + "Character name: p4, Current room: Wine Cellar, items: [], player type: computer\n"
            + "\n" + "---------------------------------\n" + "Computer p1 Look around!\n"
            + "---------------------------------\n"
            + "Now the status of players are shown as follow:\n"
            + "Target name: Doctor Lucky, Current room: Foyer\n"
            + "Character name: p1, Current room: Armory, items: [Revolver], player type: computer\n"
            + "Character name: p2, Current room: Billiard Room, items: [Billiard Cue], player type: computer\n"
            + "Character name: p3, Current room: Winter Garden, items: [], player type: computer\n"
            + "Character name: p4, Current room: Wine Cellar, items: [], player type: computer\n"
            + "\n" + "Reach the max turn, and the game end!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testMoveUp() {
    StringReader input = new StringReader("1 p1 1 3 3 1 0 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Dining Hall, items: [], player type: human]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Tennessee Room, items: [], player type: human\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n"
        + "SpecifiedRoom{name='Tennessee Room', index=17, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=8, lowerCorner=11, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Tennessee Room, items: [], player type: human]}, \n"
        + "neighbours = [Lilac Room, Master Suite, ][Trophy Room, ][Dining Hall, ][Parlor, ], \n"
        + "visibleRooms = [Lilac Room, Master Suite, Trophy Room, Dining Hall, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testMoveRight() {
    StringReader input = new StringReader("1 p1 1 3 3 1 1 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Dining Hall, items: [], player type: human]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Billiard Room, items: [], player type: human\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Billiard Room', index=1, \n"
        + "leftCorner=21, rightCorner=28, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Billiard Cue], \n"
        + "characters=[Character name: p1, Current room: Billiard Room, items: [], player type: human, Target name: Doctor Lucky, Current room: Billiard Room]}, \n"
        + "neighbours = [Trophy Room, ][][Armory, ][Dining Hall, ], \n"
        + "visibleRooms = [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testMoveLower() {
    StringReader input = new StringReader("1 p1 1 3 3 1 2 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Dining Hall, items: [], player type: human]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testMoveLeft() {
    StringReader input = new StringReader("1 p1 1 3 3 1 3 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Dining Hall, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Dining Hall', index=3, \n"
        + "leftCorner=11, rightCorner=20, upperCorner=12, lowerCorner=21, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Dining Hall, items: [], player type: human]}, \n"
        + "neighbours = [Tennessee Room, ][Billiard Room, Trophy Room, ][Armory, Wine Cellar, Drawing Room, ][Parlor, Kitchen, ], \n"
        + "visibleRooms = [Tennessee Room, Lilac Room, Master Suite, Billiard Room, Trophy Room, Armory, Wine Cellar, Drawing Room, Foyer, Piazza, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Parlor, items: [], player type: human\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Parlor', index=14, \n"
        + "leftCorner=5, rightCorner=10, upperCorner=10, lowerCorner=15, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Parlor, items: [], player type: human]}, \n"
        + "neighbours = [Servants' Quarters, ][Dining Hall, Tennessee Room, ][Kitchen, ][], \n"
        + "visibleRooms = [Servants' Quarters, Lancaster Room, Dining Hall, Tennessee Room, Trophy Room, Billiard Room, Trophy Room, Kitchen, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testInvalidMove(){
    StringReader input = new StringReader("1 p1 1 0 3 1 1 0 100 0 0 100 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid input, please enter again\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid option, please enter again.\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Carriage House\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid option, please enter again.\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Dining Hall\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid option, please enter again.\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Drawing Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid option, please enter again.\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Foyer\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testPickItem(){
    StringReader input = new StringReader("1 p1 1 0 3 2 0 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [Revolver], player type: human\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [Revolver], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testPickInvalidItem(){
    StringReader input = new StringReader("1 p1 1 0 3 2 10 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Game start! The players are listed as follow: \n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Invalid index, please enter again\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Now we are in the 1 turn\n" + "The player in current turn is: p1\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Armory', index=0, \n"
        + "leftCorner=19, rightCorner=26, upperCorner=22, lowerCorner=23, \n"
        + "items in the room =[Revolver], \n"
        + "characters=[Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }

  @Test
  public void testLookAround(){
    StringReader input = new StringReader("1 p1 1 0 1 p2 2 1 2 p3 3 10 3 3 q");
    StringBuffer gamelog = new StringBuffer();
    Controller c = new GamingConsoleController(input, gamelog);
    c.playGame(gamingModel);
    expectOutput.append("Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
        + "Please enter the name of the player:\n" + "Please enter the capacity:\n"
        + "Please enter the index of the room that the player is in:\n"
        + "Add player successfully, the current player information:\n"
        + "Target name: Doctor Lucky, Current room: Armory\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Enter 1 to add a player; Enter 2 to add a computer player; Enter 3 to start the game; Enter 4 to quit the game\n"
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
        + "characters=[Target name: Doctor Lucky, Current room: Armory, Character name: p1, Current room: Armory, items: [], player type: human]}, \n"
        + "neighbours = [Dining Hall, Billiard Room, ][][][Drawing Room, ], \n"
        + "visibleRooms = [Dining Hall, Billiard Room, Trophy Room, Library, Nursery, Tennessee Room, Lilac Room, Master Suite, Drawing Room, Wine Cellar, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "p1looks around!\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Visible Rooms: Visible room of Billiard Room: [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n"
        + "Visible Rooms: Visible room of Library: [Nursery, Trophy Room, Billiard Room, Armory, Master Suite, Lilac Room, Lancaster Room, Servants' Quarters, ]\n"
        + "Now the status of players are shown as follow:\n"
        + "Target name: Doctor Lucky, Current room: Billiard Room\n"
        + "Character name: p1, Current room: Armory, items: [], player type: human\n"
        + "Character name: p2, Current room: Billiard Room, items: [], player type: human\n"
        + "Character name: p3, Current room: Library, items: [], player type: computer\n" + "\n"
        + "Now we are in the 2 turn\n" + "The player in current turn is: p2\n"
        + "The information of the room that the player is in:\n"
        + "---------------------------------\n" + "SpecifiedRoom{name='Billiard Room', index=1, \n"
        + "leftCorner=21, rightCorner=28, upperCorner=16, lowerCorner=21, \n"
        + "items in the room =[Billiard Cue], \n"
        + "characters=[Character name: p2, Current room: Billiard Room, items: [], player type: human, Target name: Doctor Lucky, Current room: Billiard Room]}, \n"
        + "neighbours = [Trophy Room, ][][Armory, ][Dining Hall, ], \n"
        + "visibleRooms = [Trophy Room, Library, Nursery, Armory, Dining Hall, Parlor, Kitchen, ]\n"
        + "---------------------------------\n"
        + "Enter 1 to move to neighbour. Then enter the direction and index of the neighbour\n"
        + "Enter 2 to pick up an item in the room. Then enter the index of the item\n"
        + "Enter 3 to look around\n" + "Game quit!\n");
    assertEquals(expectOutput.toString(), gamelog.toString());
  }
}