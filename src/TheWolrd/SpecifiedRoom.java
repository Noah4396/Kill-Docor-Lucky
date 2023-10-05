package TheWolrd;

import java.util.ArrayList;

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


  /**
   * Constructs a Specified object.
   *
   * @param name is the name of the room.
   * @param index is the index of the room in the description file.
   * @param leftCorner is the left corner.
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
    this.neighbours.add(this.upperRooms);
    this.neighbours.add(this.rightRooms);
    this.neighbours.add(this.lowerRooms);
    this.neighbours.add(this.leftRooms);
    this.numOfNeighbours = 0;
  }
  @Override
  public void addItem(Item item){
    this.items.add(item);
  }

  public Item getItem(){
    if(items.isEmpty()){
      return null;
    }
    else{
      int itemIndex = (int)(Math.random() * items.size());
      return items.remove(itemIndex);
    }
  }

  public int getNumOfNeighbours() {
    return numOfNeighbours;
  }
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
    for(int i = 0; i < 4; i++){
      if(neighbours.get(i).contains(room)){
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
  public int getNumberOfCharacter(){
    return this.characters.size();
  }
  public Character getCharacter(int index) {
    if(index >= 0 && index < characters.size()){
      return characters.get(index);
    }
    else{
      throw new IndexOutOfBoundsException("Index is out of bound");
    }
  }
  @Override
  public void addLeftRoom(Room room) {
    if(leftRooms.contains(room))
      return;
    this.leftRooms.add(room);
    this.numOfNeighbours++;
  }
  @Override
  public void addRightRoom(Room room) {
    if(rightRooms.contains(room))
      return;
    this.rightRooms.add(room);
    this.numOfNeighbours++;
  }
  @Override
  public void addUpperRoom(Room room) {
    if(upperRooms.contains(room))
      return;
    this.upperRooms.add(room);
    this.numOfNeighbours++;
  }
  @Override
  public void addLowerRoom(Room room) {
    if(lowerRooms.contains(room))
      return;
    this.lowerRooms.add(room);
    this.numOfNeighbours++;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("SpecifiedRoom{" + "name='" + name + '\'' + ", index=" + index + ", leftCorner="
        + leftCorner + ", rightCorner=" + rightCorner + ", upperCorner=" + upperCorner
        + ", lowerCorner=" + lowerCorner + ", items=" + items + ", characters=" + characters + '}');
    sb.append(", neighbours = ");
    for(ArrayList<Room> list : neighbours){
      sb.append("[");
      for(Room room : list){
        sb.append(room.getName());
        sb.append(", ");
      }
      sb.append("]");
    }
    return sb.toString();
  }
}
