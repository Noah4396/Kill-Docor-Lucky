package TheWolrd;

import java.util.ArrayList;

/**
 * Specified rooms, eg. Dining Hall, Billiard Room.
 */
public class SpecifiedRoom implements Room {
  private String name;
  private int index;
  private int leftCorner;
  private int rightCorner;
  private int upperCorner;
  private int lowerCorner;
  private ArrayList<item> items;

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
