package world;

import java.util.Objects;

/**
 * Item in the rooms.
 */

public class Item implements Comparable<Item> {
  private String name;
  private int damagePoint;
  private int indexOfRoom;

  /**
   * Constructor.
   *
   * @param name        is the name of item.
   * @param damagePoint is the damage point.
   * @param indexOfRoom is the index of room.
   */
  public Item(String name, int damagePoint, int indexOfRoom) {
    this.name = name;
    this.damagePoint = damagePoint;
    this.indexOfRoom = indexOfRoom;
  }

  /**
   * Get the name of the item.
   *
   * @return the name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the damage point of the item.
   *
   * @return the damage point.
   */
  public int getDamagePoint() {
    return damagePoint;
  }

  /**
   * Get the index of the room.
   *
   * @return the index.
   */
  public int getIndexOfRoom() {
    return indexOfRoom;
  }

  /**
   * Set the index of the room.
   *
   * @param item is the index of the room.
   */
  public boolean isLargerThan(Item item) {
    return damagePoint > item.getDamagePoint();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(name).append(", damage point: ").append(damagePoint);
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return damagePoint == item.damagePoint && indexOfRoom == item.indexOfRoom && Objects.equals(
        name, item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, damagePoint, indexOfRoom);
  }

  @Override
  public int compareTo(Item item) {
    return this.damagePoint - item.getDamagePoint();
  }
}
