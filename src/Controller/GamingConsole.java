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
  private int numOfItems;

  public GamingConsole(String path) {
    this.rooms = new ArrayList<>();
    this.items = new ArrayList<>();
    parse(path);
    for(Room room : rooms){
      setNeighbour(room);
    }
    for(Room room : rooms)
      System.out.println(room);
  }
  private void parse(String path){
    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      // The first line reader: 36 30 Doctor Lucky's Mansion;
      parseFirstLine(br.readLine());

      // The second line reader: 50 Doctor Lucky
      parseSecondLine(br.readLine());

      // Start to parse Rooms
      this.numOfRooms = parseNumber(br.readLine());
      for(int i = 0; i < numOfRooms; i++){
        parseRoom(br.readLine(), i);
      }

      // Start to parse items
      this.numOfItems = parseNumber(br.readLine());
      for(int i = 0; i < numOfItems; i++){
        parseItem(br.readLine(), i);
      }

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

  public int parseNumber(String line){
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    return Integer.parseInt(words[0]);
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
    //System.out.println(room);
    this.rooms.add(room);
    for(int i = upperBound; i <= lowerBound; i++){
      for(int j = leftBound; j <= rightBound; j++){
        chessBoard[i][j] = index;
      }
    }
  }
  private void parseItem(String line, int index) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    int indexOfRoom = Integer.parseInt(words[0]);
    int damagePoint = Integer.parseInt(words[1]);
    String itemName = line.substring(line.indexOf(words[2]));

    Item item = new Item(itemName, damagePoint, indexOfRoom);
    rooms.get(indexOfRoom).addItem(item);
  }

  public void setNeighbour(Room room){
    int tmp;
    Room tmpRoom;
    int leftBound = room.getLeftCorner();
    int rightBound = room.getRightCorner();
    int upperBound = room.getUpperCorner();
    int lowerBound = room.getLowerCorner();

    if(leftBound > 0){
      tmp = chessBoard[upperBound][leftBound - 1];
      if(tmp != -1){
        tmpRoom = rooms.get(tmp);
        tmpRoom.addRightRoom(room);
        room.addLeftRoom(tmpRoom);
      }

      tmp = chessBoard[lowerBound][leftBound - 1];
      if(tmp != -1){
        tmpRoom = rooms.get(tmp);
        tmpRoom.addRightRoom(room);
        room.addLeftRoom(tmpRoom);
      }
    }

    if(upperBound > 0) {
      tmp = chessBoard[upperBound - 1][leftBound];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addLowerRoom(room);
        room.addUpperRoom(tmpRoom);
      }

      tmp = chessBoard[upperBound - 1][rightBound];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addLowerRoom(room);
        room.addUpperRoom(tmpRoom);
      }
    }

    if(rightBound < width - 1) {
      tmp = chessBoard[upperBound][rightBound + 1];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addLeftRoom(room);
        room.addRightRoom(tmpRoom);
      }

      tmp = chessBoard[lowerBound][rightBound + 1];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addLeftRoom(room);
        room.addRightRoom(tmpRoom);
      }
    }

    if(lowerBound < height - 1) {
      tmp = chessBoard[lowerBound + 1][leftBound];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addUpperRoom(room);
        room.addLowerRoom(tmpRoom);
      }

      tmp = chessBoard[lowerBound + 1][rightBound];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addUpperRoom(room);
        room.addLowerRoom(tmpRoom);
      }
    }
  }
}
