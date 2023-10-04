package Controller;

import TheWolrd.Character;
import TheWolrd.Item;
import TheWolrd.Room;
import TheWolrd.TargetCharacter;

import java.util.ArrayList;

public class GamingConsole {
  private int[][] chessBoard;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;
  private ArrayList<Character> players;
  private TargetCharacter doctorLucky;

  public GamingConsole(String path) {
    parse(path);
  }
  private void parse(String path){

  }
  public void move(Character character, Room room){
    Room prevRoom = character.getRoom();
    if(prevRoom == room)
      return;
    prevRoom.removeCharacter(character);
    room.addCharacter(character);
    character.setRoom(room);
  }

}
