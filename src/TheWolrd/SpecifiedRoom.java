package TheWolrd;

import java.util.ArrayList;

/**
 * Specified rooms, eg. Dining Hall, Billiard Room.
 */
public class SpecifiedRoom implements Room {
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
  }

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

  public int getIndex() {
    return index;
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

  public void addLeftRoom(Room room) {
    this.leftRooms.add(room);
  }

  public void addRightRoom(Room room) {
    this.rightRooms.add(room);
  }

  public void addUpperRoom(Room room) {
    this.upperRooms.add(room);
  }

  public void addLowerRoom(Room room) {
    this.lowerRooms.add(room);
  }

}
