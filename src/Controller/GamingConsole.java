package Controller;

import TheWolrd.Item;
import TheWolrd.Room;

import java.util.ArrayList;

public class GamingConsole {
  private int[][] chessBoard;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;

  public GamingConsole(int width, int height) {
    this.chessBoard = new int[height][width];
    this.rooms  = new ArrayList<>();
    this.items = new ArrayList<>();
  }
}
