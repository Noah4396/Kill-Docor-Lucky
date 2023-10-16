package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

import world.Character;
import world.Item;
import world.PlayerCharacter;
import world.Room;
import world.SpecifiedRoom;
import world.TargetCharacter;

/**
 * The Gaming console.
 */
public class GamingModel {
  private int[][] chessBoard;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;
  private ArrayList<PlayerCharacter> players;
  private TargetCharacter doctorLucky;
  private String name;
  private int height;
  private int width;
  private int numOfRooms;
  private int numOfItems;
  private int amplifierDegree;
  private BufferedImage image;
  private Graphics2D g2d;
  private int currentTurn;
  private int totalTurn;
  private int maxTurn;
  private long seed;
  private Random random;
  private Boolean gameOver;

  /**
   * The constructor.
   *
   * @param path is thr origin path of source file.
   */
  public GamingModel(String path, int maxTurn) {
    this.rooms = new ArrayList<>();
    this.items = new ArrayList<>();
    this.players = new ArrayList<>();
    this.amplifierDegree = 30;
    this.totalTurn = 0;
    this.currentTurn = 0;
    this.maxTurn = maxTurn;
    this.seed = 123;
    this.random = new Random(seed);
    gameOver = false;
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

  /**
   * Paint the rooms.
   *
   * @param room the painted room.
   */
  private void paintRoom(Room room) {
    g2d.setColor(Color.BLACK);
    int x = room.getLeftCorner() * amplifierDegree;
    int y = room.getUpperCorner() * amplifierDegree;
    int w = room.getRightCorner() - room.getLeftCorner() + 1;
    w *= amplifierDegree;
    int h = room.getLowerCorner() - room.getUpperCorner() + 1;
    h *= amplifierDegree;
    g2d.drawRect(x, y, w, h);
    g2d.drawString(room.getName(), x + w / 3, y + h / 2);
  }

  /**
   * Output the image.
   */
  public void outputImage() {
    try {
      File output = new File("./res/example.png");
      ImageIO.write(image, "png", output);
      System.out.println("Image saved successfully.");
    } catch (IOException e) {
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

  /**
   * Move a character to a room.
   *
   * @param character the character.
   * @param room      the room.
   */
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

  /**
   * Move the target.
   */
  public void moveTarget() {
    int targetIndex = doctorLucky.getRoom().getIndex();
    targetIndex = (targetIndex + 1) % rooms.size();
    move(doctorLucky, rooms.get(targetIndex));
  }

  /**
   * Parse the first line.
   *
   * @param line the line.
   */
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

  /**
   * Parse the second line.
   *
   * @param line the second line.
   */
  public void parseSecondLine(String line) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");

    doctorLucky = new TargetCharacter();
    doctorLucky.setHealth(Integer.parseInt(words[0]));
    doctorLucky.setName(line.substring(line.indexOf(words[1])));

  }

  /**
   * Parse number.
   *
   * @param line the line.
   * @return the int.
   */
  public int parseNumber(String line) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    return Integer.parseInt(words[0]);
  }

  /**
   * Parse the room line.
   *
   * @param line  the line.
   * @param index the index of the room.
   */
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
        if (chessBoard[i][j] != -1) {
          throw new IllegalArgumentException("The Rooms overlap!");
        }
        chessBoard[i][j] = index;
      }
    }
  }

  /**
   * Parse the items.
   *
   * @param line  the line.
   * @param index the index of the item.
   */
  private void parseItem(String line, int index) {
    line = line.replaceFirst("^\\s*", "");
    String[] words = line.split("\\s+");
    int indexOfRoom = Integer.parseInt(words[0]);
    int damagePoint = Integer.parseInt(words[1]);
    String itemName = line.substring(line.indexOf(words[2]));

    Item item = new Item(itemName, damagePoint, indexOfRoom);
    rooms.get(indexOfRoom).addItem(item);
  }

  /**
   * Set neighbour of a room.
   *
   * @param room the room.
   */
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

  /**
   * Get the target.
   *
   * @return the target.
   */
  public TargetCharacter getDoctorLucky() {
    return doctorLucky;
  }

  /**
   * Display all the rooms.
   *
   * @return the displayedRooms.
   */
  public String displayRooms() {
    StringBuffer sb = new StringBuffer();
    for (Room room : rooms) {
      sb.append(room);
    }
    return sb.toString();
  }

  public String displayers() {
    return players.toString();
  }

  /**
   * Display room of index.
   *
   * @param index the index of room
   * @return the display.
   */
  public String displayRoom(int index) {
    if (index >= rooms.size() || index < 0) {
      throw new IllegalArgumentException("Index out of bound");
    }
    return rooms.get(index).toString();
  }

  /**
   * Display all neighbours.
   *
   * @return the displayed neighbours.
   */
  public String displayNAllNeighbours() {
    StringBuffer sb = new StringBuffer();
    for (Room room : rooms) {
      for (int i = 0; i < 4; i++) {
        sb.append(room.displayNeighbours(i));
      }
    }
    return sb.toString();
  }

  /**
   * Display all visible rooms.
   *
   * @return to the String of displayed rooms
   */
  public String displayVisibleRooms() {
    StringBuffer sb = new StringBuffer();
    for (Room room : rooms) {
      sb.append(room.displayVisibleRooms());
    }
    return sb.toString();
  }

  /**
   * Add a player playing the game.
   *
   * @param p is the player added to the game.
   */
  public void addPlayer(PlayerCharacter p, int roomIndex) {
    if (p == null || p.getName().isEmpty()) {
      throw new IllegalArgumentException("Invalid player");
    } else {
      players.add(p);
      move(p, rooms.get(roomIndex));
    }
  }

  /**
   * Let the player pick up an item from its room, if the room do not have an item or the player
   * cannot pick more item, it will throw IllegalStateException.
   *
   * @param p     the player that will pick item
   * @param index the item index;
   */
  public void pickUpItem(PlayerCharacter p, int index) {
    if (!p.isAbleToPick() || p.getRoom() == null || p.getRoom().getItemsNumber() == 0) {
      throw new IllegalStateException("Cannot pick up item");
    } else if (index > p.getRoom().getItemsNumber() || index < 1) {
      throw new IllegalArgumentException("Invalid index");
    } else {
      p.pickItem(p.getRoom().deleteItem(index - 1));
    }
  }

  /**
   * Move character to neighbour.
   *
   * @param c         the moved character.
   * @param direction the moving direction.
   * @param index     the index of room in direction.
   */
  public void moveToNeighbour(Character c, int direction, int index) {
    move(c, c.getRoom().getNeighbour(direction, index));
  }

  /**
   * Look around.
   *
   * @param c the character that looks around.
   * @return information of other players.
   */
  public String lookAround(Character c) {
    if (c == null) {
      throw new IllegalArgumentException("Invalid player");
    }
    StringBuffer sb = new StringBuffer();
    for (PlayerCharacter character : players) {
      if (!c.equals(character)) {
        sb.append(character.toString());
        sb.append("\nVisible Rooms: ");
        sb.append(character.getRoom().displayVisibleRooms() + "\n");
      }
    }
    return sb.toString();
  }

  /**
   * Get the current turn.
   *
   * @return the current player.
   */
  public PlayerCharacter getCurrentPlayer() {
    return players.get(currentTurn);
  }

  /**
   * Pass the turn.
   */
  public void passTurn() {
    currentTurn++;
    currentTurn = currentTurn % players.size();
    totalTurn++;
    if (totalTurn >= maxTurn) {
      gameOver = true;
    }
  }

  /**
   * check if it is game over.
   * @return true if game over.
   */
  public boolean isGameOver(){
    return gameOver;
  }
}
