package Controller;

import TheWolrd.*;
import TheWolrd.Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GamingConsole {
  private int[][] chessBoard;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;
  private ArrayList<Character> players;
  private TargetCharacter doctorLucky;
  private String name;
  private int height;
  private int width;
  private int numOfRooms;

  public GamingConsole(String path) {
    parse(path);
  }
  private void parse(String path){
    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      // The first line reader: 36 30 Doctor Lucky's Mansion;
      parseFirstLine(br.readLine());

      // The second line reader: 50 Doctor Lucky
      parseSecondLine(br.readLine());

      // The third line contains the number of rooms
      parseThirdLine(br.readLine());
      // Start to parse Rooms
      this.rooms = new ArrayList<>();
      for(int i = 0; i < numOfRooms; i++){
        parseRoom(br.readLine(), i);
      }
      //for(Room room : rooms)
        //System.out.println(room);

    } catch(IOException e){
      e.printStackTrace();
    }
  }
  public void move(Character character, Room room){
    Room prevRoom = character.getRoom();
    if(prevRoom == room)
      return;
    prevRoom.removeCharacter(character);
    room.addCharacter(character);
    character.setRoom(room);
  }

  public void parseFirstLine(String line){
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");

    height = Integer.parseInt(words[0]);
    width = Integer.parseInt(words[1]);
    chessBoard = new int[height][width];
    for(int[] iter: chessBoard){
      Arrays.fill(iter, -1);
    }
    this.name = line.substring(line.indexOf(words[2]));
  }

  public void parseSecondLine(String line){
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");

    doctorLucky = new TargetCharacter();
    doctorLucky.setHealth(Integer.parseInt(words[0]));
    doctorLucky.setName(line.substring(line.indexOf(words[1])));

  }

  public  void parseThirdLine(String line){
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    this.numOfRooms = Integer.parseInt(words[0]);
  }

  public void parseRoom(String line, int index){
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    int upperBound = Integer.parseInt(words[0]);
    int leftBound = Integer.parseInt(words[1]);
    int lowerBound = Integer.parseInt(words[2]);
    int rightBound = Integer.parseInt(words[3]);
    String name = line.substring(line.indexOf(words[4]));

    Room room = new SpecifiedRoom(name, index, leftBound, rightBound, upperBound, lowerBound);
    System.out.println(room);
    this.rooms.add(room);
    for(int i = upperBound; i <= lowerBound; i++){
      for(int j = leftBound; j <= rightBound; j++){
        chessBoard[i][j] = index;
      }
    }
  }

}
