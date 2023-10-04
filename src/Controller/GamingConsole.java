package Controller;

import TheWolrd.Character;
import TheWolrd.Item;
import TheWolrd.Room;
import TheWolrd.TargetCharacter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      String line;
      while((line = br.readLine()) != null){
        System.out.println(line);
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

}
