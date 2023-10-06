package controller;

import world.*;
import world.Character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
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
  private int amplifierDegree;
  private BufferedImage image;
  private Graphics2D g2d;

  public GamingConsole(String path) {
    this.rooms = new ArrayList<>();
    this.items = new ArrayList<>();
    this.amplifierDegree = 30;
    try {
      parse(path);
    } catch (IllegalArgumentException e) {
      System.err.println("Find rooms overlap");
    }
    move(doctorLucky, rooms.get(0));

    this.image = new BufferedImage(width * amplifierDegree, height * amplifierDegree,
        BufferedImage.TYPE_3BYTE_BGR);
    g2d = image.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, this.width * amplifierDegree, this.height * amplifierDegree);
    g2d.setFont(new Font("Arial", Font.BOLD, 12));

    for (Room room : rooms) {
      setNeighbour(room);
    }
    for (Room room : rooms) {
      room.setVisibleRooms();
      paintRoom(room);
      //System.out.println(room);
    }

    //outputImage();
  }

  private void paintRoom(Room room) {
    g2d.setColor(Color.BLACK);
    int x = room.getLeftCorner();
    int y = room.getUpperCorner();
    int width = room.getRightCorner() - room.getLeftCorner() + 1;
    int height = room.getLowerCorner() - room.getUpperCorner() + 1;
    x *= amplifierDegree;
    y *= amplifierDegree;
    width *= amplifierDegree;
    height *= amplifierDegree;
    g2d.drawRect(x, y, width, height);
    g2d.drawString(room.getName(), x + width / 3, y + height / 2);
  }

  public void outputImage() {
    try {
      File output = new File("./res/example.png");
      ImageIO.write(image, "png", output);
      System.out.println("Image saved successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void parse(String path) {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      // The first line reader: 36 30 Doctor Lucky's Mansion;
      parseFirstLine(br.readLine());

      // The second line reader: 50 Doctor Lucky
      parseSecondLine(br.readLine());

      // Start to parse Rooms
      this.numOfRooms = parseNumber(br.readLine());
      for (int i = 0; i < numOfRooms; i++) {
        parseRoom(br.readLine(), i);
      }

      // Start to parse items
      this.numOfItems = parseNumber(br.readLine());
      for (int i = 0; i < numOfItems; i++) {
        parseItem(br.readLine(), i);
      }

    } catch (IOException e) {
      System.err.println("Invalid path");
      //e.printStackTrace();
    }
  }

  public void move(Character character, Room room) {
    Room prevRoom = character.getRoom();
    if (prevRoom == room) {
      return;
    }
    if (prevRoom != null) {
      prevRoom.removeCharacter(character);
    }
    room.addCharacter(character);
    character.setRoom(room);
  }
  public void moveTarget() {
    int targetIndex = doctorLucky.getRoom().getIndex();
    targetIndex = (targetIndex + 1) % rooms.size();
    move(doctorLucky, rooms.get(targetIndex));
  }
  public void parseFirstLine(String line) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");

    height = Integer.parseInt(words[0]);
    width = Integer.parseInt(words[1]);
    chessBoard = new int[height][width];
    for (int[] iter : chessBoard) {
      Arrays.fill(iter, -1);
    }
    this.name = line.substring(line.indexOf(words[2]));
  }

  public void parseSecondLine(String line) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");

    doctorLucky = new TargetCharacter();
    doctorLucky.setHealth(Integer.parseInt(words[0]));
    doctorLucky.setName(line.substring(line.indexOf(words[1])));

  }

  public int parseNumber(String line) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    return Integer.parseInt(words[0]);
  }

  public void parseRoom(String line, int index) {
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
    for (int i = upperBound; i <= lowerBound; i++) {
      for (int j = leftBound; j <= rightBound; j++) {
        if (chessBoard[i][j] != -1)
          throw new IllegalArgumentException("The Rooms overlap!");
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

  public void setNeighbour(Room room) {
    int tmp;
    Room tmpRoom;
    int leftBound = room.getLeftCorner();
    int rightBound = room.getRightCorner();
    int upperBound = room.getUpperCorner();
    int lowerBound = room.getLowerCorner();

    if (leftBound > 0) {
      tmp = chessBoard[upperBound][leftBound - 1];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addRightRoom(room);
        room.addLeftRoom(tmpRoom);
      }

      tmp = chessBoard[lowerBound][leftBound - 1];
      if (tmp != -1) {
        tmpRoom = rooms.get(tmp);
        tmpRoom.addRightRoom(room);
        room.addLeftRoom(tmpRoom);
      }
    }

    if (upperBound > 0) {
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

    if (rightBound < width - 1) {
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

    if (lowerBound < height - 1) {
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

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("Mansion Name: ");
    sb.append(this.name);
    sb.append("\n");

    sb.append("Target name: ");
    sb.append(this.doctorLucky.getName());
    sb.append("\n");

    sb.append("Number of Rooms: ");
    sb.append(this.numOfRooms);
    sb.append("\n");

    sb.append("Number of Items: ");
    sb.append(this.numOfItems);
    sb.append("\n");

    sb.append("Target in the Room: ");
    sb.append(this.doctorLucky.getRoom());
    sb.append("\n");
    return sb.toString();
  }

  public TargetCharacter getDoctorLucky() {
    return doctorLucky;
  }
  public void displayRooms(){
    for(Room room : rooms){
      System.out.println(room);
    }
  }
  public void displayNeighbours(){
    for(Room room : rooms){
      System.out.println(room.displayNeighbours());
    }
  }
  public void displayVisibleRooms(){
    for(Room room : rooms){
      System.out.println(room.displayVisibleRooms());
    }
  }
}
