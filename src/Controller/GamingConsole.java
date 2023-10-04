package Controller;

import TheWolrd.Character;
import TheWolrd.Item;
import TheWolrd.Room;
import TheWolrd.TargetCharacter;

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

  public GamingConsole(String path) {
    parse(path);
  }
  private void parse(String path){
    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      // The first line reader: 36 30 Doctor Lucky's Mansion;
      String line = br.readLine();
      String[] words = line.split(" ");

      height = Integer.parseInt(words[0]);
      width = Integer.parseInt(words[1]);
      chessBoard = new int[height][width];
      for(int[] iter: chessBoard){
        Arrays.fill(iter, -1);
      }
      this.name = line.substring(line.indexOf(words[2]));

      // The second line reader: 50 Doctor Lucky
      line = br.readLine();
      words = line.split(" ");




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
