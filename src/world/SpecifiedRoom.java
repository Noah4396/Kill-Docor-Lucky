package world;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Specified rooms, eg. Dining Hall, Billiard Room.
 */
public class SpecifiedRoom implements Room {
  private int numOfNeighbours;
  private final String name;
  private final int index;
  private final int leftCorner;
  private final int rightCorner;
  private final int upperCorner;
  private final int lowerCorner;
  private ArrayList<Item> items;
  private ArrayList<Room> leftRooms;
  private ArrayList<Room> rightRooms;
  private ArrayList<Room> upperRooms;
  private ArrayList<Room> lowerRooms;
  private ArrayList<Character> characters;
  private ArrayList<ArrayList<Room>> neighbours;
  private ArrayList<Room> visibleRooms;

  /**
   * Constructs a Specified object.
   *
   * @param name        is the name of the room.
   * @param index       is the index of the room in the description file.
   * @param leftCorner  is the left corner.
   * @param rightCorner is the right corner.
   * @param upperCorner is the upper corner.
   * @param lowerCorner is the lower corner.
   */

  public SpecifiedRoom(String name, int index, int leftCorner, int rightCorner, int upperCorner,
      int lowerCorner) {
    this.name = name;
    this.index = index;
    this.leftCorner = leftCorner;
    this.rightCorner = rightCorner;
    this.upperCorner = upperCorner;
    this.lowerCorner = lowerCorner;
    this.leftRooms = new ArrayList<>();
    this.rightRooms = new ArrayList<>();
    this.upperRooms = new ArrayList<>();
    this.lowerRooms = new ArrayList<>();
    this.items = new ArrayList<>();
    this.characters = new ArrayList<>();
    this.neighbours = new ArrayList<>();
    this.visibleRooms = new ArrayList<>();
    this.neighbours.add(this.upperRooms);
    this.neighbours.add(this.rightRooms);
    this.neighbours.add(this.lowerRooms);
    this.neighbours.add(this.leftRooms);
    this.numOfNeighbours = 0;
  }

  @Override
  public void addItem(Item item) {
    this.items.add(item);
  }

  /**
   * Get a random item in the room.
   *
   * @return item
   */
  public Item getItem() {
    if (items.isEmpty()) {
      return null;
    } else {
      int itemIndex = (int) (Math.random() * items.size());
      return items.remove(itemIndex);
    }
  }

  @Override
  public int getNumOfNeighbours() {
    return numOfNeighbours;
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean hasLeftRoom() {
    return !leftRooms.isEmpty();
  }

  @Override
  public boolean hasRightRoom() {
    return !rightRooms.isEmpty();
  }

  @Override
  public boolean hasUpperRoom() {
    return !upperRooms.isEmpty();
  }

  @Override
  public boolean hasLowerRoom() {
    return !lowerRooms.isEmpty();
  }

  @Override
  public void addCharacter(Character character) {
    this.characters.add(character);
  }

  @Override
  public void removeCharacter(Character character) {
    this.characters.remove(character);
  }

  @Override
  public int isNeighbour(Room room) {
    for (int i = 0; i < 4; i++) {
      if (neighbours.get(i).contains(room)) {
        return i + 1;
      }
    }
    return 0;
  }

  @Override
  public int getLeftCorner() {
    return leftCorner;
  }

  @Override
  public int getRightCorner() {
    return rightCorner;
  }

  @Override
  public int getUpperCorner() {
    return upperCorner;
  }

  @Override
  public int getLowerCorner() {
    return lowerCorner;
  }

  /**
   * Get the number of character in the room.
   * @return number of character.
   */
  public int getNumberOfCharacter() {
    return this.characters.size();
  }

  /**
   * Get the character with the index.
   * @param index of the character.
   * @return the character.
   */
  public Character getCharacter(int index) {
    if (index >= 0 && index < characters.size()) {
      return characters.get(index);
    } else {
      throw new IndexOutOfBoundsException("Index is out of bound");
    }
  }

  @Override
  public void addLeftRoom(Room room) {
    if (leftRooms.contains(room)) {
      return;
    }
    this.leftRooms.add(room);
    this.numOfNeighbours++;
  }

  @Override
  public void addRightRoom(Room room) {
    if (rightRooms.contains(room)) {
      return;
    }
    this.rightRooms.add(room);
    this.numOfNeighbours++;
  }

  @Override
  public void addUpperRoom(Room room) {
    if (upperRooms.contains(room)) {
      return;
    }
    this.upperRooms.add(room);
    this.numOfNeighbours++;
  }

  @Override
  public void addLowerRoom(Room room) {
    if (lowerRooms.contains(room)) {
      return;
    }
    this.lowerRooms.add(room);
    this.numOfNeighbours++;
  }

  @Override
  public void setVisibleRooms() {
    Stack<Room> stack = new Stack<>();
    ArrayList<Room> list;
    for (int i = 0; i < 4; i++) {
      list = neighbours.get(i);
      for (Room room : list) {
        stack.push(room);
        visibleRooms.add(room);
      }
      while (!stack.isEmpty()) {
        Room tmp = stack.pop();
        list = tmp.getNeighbours(i);
        for (Room room : list) {
          stack.push(room);
          visibleRooms.add(room);
        }
      }
    }
  }

  @Override
  public ArrayList<Room> getNeighbours(int index) {
    return new ArrayList<>(neighbours.get(index));
  }

  /**
   * Get items.
   * @return all the items.
   */
  public ArrayList<Item> getItems() {
    return new ArrayList<>(items);
  }

  /**
   * Get left rooms.
   * @return all the left rooms.
   */
  public ArrayList<Room> getLeftRooms() {
    return new ArrayList<>(leftRooms);
  }

  /**
   * Get right rooms.
   * @return all right rooms.
   */
  public ArrayList<Room> getRightRooms() {
    return new ArrayList<>(rightRooms);
  }

  /**
   * Get upper rooms.
   * @return all upper rooms.
   */
  public ArrayList<Room> getUpperRooms() {
    return new ArrayList<>(upperRooms);
  }

  /**
   * Get lower rooms.
   * @return all lower rooms.
   */
  public ArrayList<Room> getLowerRooms() {
    return new ArrayList<>(lowerRooms);
  }

  /**
   * Get characters.
   * @return all characters.
   */
  public ArrayList<Character> getCharacters() {
    return new ArrayList<>(characters);
  }

  /**
   * Get visible rooms.
   * @return all of visible rooms.
   */
  public ArrayList<Room> getVisibleRooms() {
    return new ArrayList<>(visibleRooms);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer(
        "SpecifiedRoom{" + "name='" + name + '\'' + ", index=" + index + ", leftCorner="
            + leftCorner + ", rightCorner=" + rightCorner + ", upperCorner=" + upperCorner
            + ", lowerCorner=" + lowerCorner + ", items=" + items + ", characters=" + characters
            + '}');
    sb.append(", neighbours = ");
    for (ArrayList<Room> list : neighbours) {
      sb.append("[");
      for (Room room : list) {
        sb.append(room.getName());
        sb.append(", ");
      }
      sb.append("]");
    }
    sb.append(", visibleRooms = [");
    for (Room room : visibleRooms) {
      sb.append(room.getName());
      sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public String displayNeighbours() {
    StringBuffer sb = new StringBuffer("Neighbours of " + this.name + " in clockwise: ");
    for (ArrayList<Room> list : neighbours) {
      sb.append("[");
      for (Room room : list) {
        sb.append(room.getName());
        sb.append(", ");
      }
      sb.append("]");
    }
    return sb.toString();
  }

  @Override
  public String displayVisibleRooms() {
    StringBuffer sb = new StringBuffer("Visible room of " + this.name + ": [");
    for (Room room : visibleRooms) {
      sb.append(room.getName());
      sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
