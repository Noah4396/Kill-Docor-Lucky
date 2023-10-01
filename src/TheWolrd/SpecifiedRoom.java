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
    this.items = new ArrayList<>();
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
  @Override
  public boolean hasLeftRoom() {
    return false;
  }

  @Override
  public boolean hasRightRoom() {
    return false;
  }

  @Override
  public boolean hasUpperRoom() {
    return false;
  }

  @Override
  public boolean hasLowerRoom() {
    return false;
  }
}
