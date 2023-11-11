package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

import world.Character;
import world.Item;
import world.Pet;
import world.PlayerCharacter;
import world.Room;
import world.SpecifiedRoom;
import world.TargetCharacter;

/**
 * The Gaming console.
 */
public class GamingModel implements Model {
  private int[][] chessBoard;
  private ArrayList<Room> rooms;
  private ArrayList<Item> items;
  private ArrayList<PlayerCharacter> players;
  private TargetCharacter doctorLucky;
  private Pet pet;
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
  private int[] visited;
  private Stack<Integer> stack;

  /**
   * The constructor.
   *
   * @param path    is thr origin path of source file.
   * @param maxTurn is the max turn of the game.
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
    visited = new int[rooms.size()];
    stack = new Stack<>();
    petInitialize(0);
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

    outputImage("./res/example.png");
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
  public void outputImage(String path) {
    try {
      File output = new File(path);
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

      parseThirdLine(br.readLine());
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
   * Parse the pet.
   *
   * @param s the line.
   */
  private void parseThirdLine(String s) {
    this.pet = new Pet(s);
  }

  /**
   * Move a character to a room.
   *
   * @param character the character.
   * @param room      the room.
   */
  public void move(Character character, Room room) {
    if (character == null || room == null) {
      throw new IllegalArgumentException("Invalid input of move");
    }
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

  @Override
  public void moveTarget() {
    int targetIndex = doctorLucky.getRoom().getIndex();
    targetIndex = (targetIndex + 1) % rooms.size();
    move(doctorLucky, rooms.get(targetIndex));
  }

  @Override
  public void movePetDepthFirst() {
    ArrayList<Room> neighbours = pet.getRoom().getVisibleRooms();
    for (Room room : neighbours) {
      if (visited[room.getIndex()] == 0) {
        stack.push(room.getIndex());
        visited[room.getIndex()] = 1;
      }
    }
    if (stack.isEmpty()) {
      petInitialize(0);
      return;
    }
    int index = stack.pop();
    move(pet, rooms.get(index));
  }

  private void petInitialize(int index) {
    Arrays.fill(visited, 0);
    visited[index] = 1;
    move(pet, rooms.get(index));
  }

  public String petInfo() {
    return pet.getRoom().getName();
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

  @Override
  public String displayRooms() {
    StringBuffer sb = new StringBuffer();
    for (Room room : rooms) {
      sb.append(room);
      sb.append("\n\n");
    }
    return sb.toString();
  }

  @Override
  public String displayers() {
    StringBuffer sb = new StringBuffer();
    sb.append(doctorLucky.toString());
    sb.append("\n");
    for (PlayerCharacter p : players) {
      sb.append(p.toString());
      sb.append("\n");
    }
    return sb.toString();
  }

  @Override
  public String displayRoom(int index) {
    if (index >= rooms.size() || index < 0) {
      throw new IllegalArgumentException("Index out of bound");
    }
    return rooms.get(index).toString();
  }

  @Override
  public String displayTargetAndPet() {
    StringBuffer sb = new StringBuffer();
    sb.append("Target room: ");
    sb.append(doctorLucky.getRoom().getName());
    sb.append("\n");
    sb.append("Pet room: ");
    sb.append(pet.getRoom().getName());
    sb.append("\n");
    return sb.toString();
  }

  /**
   * Display all neighbours.
   *
   * @return the displayed neighbours.
   */
  public String displayAllNeighbours() {
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
      sb.append(room.displayVisibleRooms(pet.getRoom().getIndex()));
    }
    return sb.toString();
  }

  public boolean isVisibleBy(Character observer, Character killer) {
    if (observer == null || killer == null) {
      throw new IllegalArgumentException("");
    }
    if (observer.getRoom().getIndex() == killer.getRoom().getIndex()) {
      return true;
    }
    if (pet.getRoom().getIndex() == killer.getRoom().getIndex()) {
      return false;
    }
    return killer.getRoom().isNeighbour(observer.getRoom()) != 0;
  }

  @Override
  public void addPlayer(PlayerCharacter p, int roomIndex) {
    if (p == null || p.getName().isEmpty() || roomIndex < 0 || roomIndex >= rooms.size()) {
      throw new IllegalArgumentException("Invalid input");
    }
    for (PlayerCharacter tmp : players) {
      if (tmp.getName().equals(p.getName())) {
        throw new IllegalArgumentException("Invalid input");
      }
    }
    players.add(p);
    move(p, rooms.get(roomIndex));
  }

  @Override
  public PlayerCharacter getTurn() {
    return players.get(currentTurn);
  }

  @Override
  public int getTotalTurn() {
    return totalTurn;
  }

  @Override
  public void pickUpItem(PlayerCharacter p, int index) {
    if (p.isComputer()) {
      int rand = random.nextInt(100);
      rand = rand % p.getRoom().getItemsNumber();
      p.pickItem(p.getRoom().deleteItem(rand));
      return;
    }
    if (!p.isAbleToPick() || p.getRoom() == null || p.getRoom().getItemsNumber() == 0) {
      throw new IllegalStateException("Cannot pick up item");
    } else if (index >= p.getRoom().getItemsNumber() || index < 0) {
      throw new IllegalArgumentException("Invalid index");
    } else {
      p.pickItem(p.getRoom().deleteItem(index));
    }
  }

  @Override
  public void moveToNeighbour(Character c, int direction, int index) {
    if (c == null || !players.contains(c)) {
      throw new IllegalArgumentException("Invalid Player");
    }
    if (c.isComputer()) {
      move(c, c.getRoom().getRandNeighbour(random.nextInt(100)));
      return;
    }
    try {
      move(c, c.getRoom().getNeighbour(direction, index));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid input");
    }
  }

  @Override
  public void movePet(Character c, int direction, int index) {
    if (c.getRoom().getIndex() != pet.getRoom().getIndex()) {
      throw new IllegalStateException("Not in the same room");
    }
    if (c.isComputer()) {
      move(pet, c.getRoom().getRandNeighbour(random.nextInt(100)));
    } else {
      try {
        move(pet, c.getRoom().getNeighbour(direction, index));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid input");
      }
    }
    Arrays.fill(visited, 0);
    visited[pet.getRoom().getIndex()] = 1;
    stack.clear();
  }

  @Override
  public String lookAround(Character c) {
    if (c == null || !players.contains(c)) {
      throw new IllegalArgumentException("Invalid player");
    }
    StringBuffer sb = new StringBuffer();
    sb.append(c.getName() + " looks around!\n");
    //    for (PlayerCharacter character : players) {
    //      if (!c.equals(character)) {
    //        sb.append(character.toString());
    //        sb.append("\nVisible Rooms: ");
    //        sb.append(character.getRoom().displayVisibleRooms() + "\n");
    //      }
    //    }
    sb.append("Information of the current room:\n");
    sb.append(c.getRoom().toString() + "\n\n");
    sb.append(c.getRoom().displayVisibleRooms(pet.getRoom().getIndex()));
    return sb.toString();
  }

  @Override
  public void computerCommand(PlayerCharacter player, Appendable out) {
    int choice = 3;
    if (!player.isAbleToPick() || player.getRoom().getItemsNumber() <= 0) {
      choice = 2;
    }
    choice = random.nextInt(choice);
    switch (choice) {
      case 0:
        outputString("Computer " + player.getName() + " moves to the neighbour!\n", out);
        moveToNeighbour(player, 0, 0);
        break;
      case 1:
        outputString("Computer " + player.getName() + " Look around!\n", out);
        lookAround(player);
        break;
      case 2:
        outputString("Computer " + player.getName() + " pick up an item!\n", out);
        pickUpItem(player, 0);
        break;
      default:
        break;
    }
  }

  @Override
  public void attempt(PlayerCharacter player, int index){

  }

  private void outputString(String s, Appendable out) {
    try {
      out.append(s);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
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
  @Override
  public void passTurn() {
    currentTurn++;
    currentTurn = currentTurn % players.size();
    totalTurn++;
    moveTarget();
    movePetDepthFirst();
    if (totalTurn >= maxTurn) {
      gameOver = true;
    }
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }
}
