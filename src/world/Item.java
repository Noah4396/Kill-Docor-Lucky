package world;

import java.util.Objects;

/**
 * Item in the rooms.
 */

public class Item {
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

  public String getName() {
    return name;
  }

  public int getDamagePoint() {
    return damagePoint;
  }

  public int getIndexOfRoom() {
    return indexOfRoom;
  }

  public boolean isLargerThan(Item item) {
    return damagePoint > item.getDamagePoint();
  }

  @Override
  public String toString() {
    return "Item{" + "name='" + name + '\'' + ", damagePoint=" + damagePoint + ", indexOfRoom="
        + indexOfRoom + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Item item = (Item) o;
    return damagePoint == item.damagePoint && indexOfRoom == item.indexOfRoom && Objects.equals(
        name, item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, damagePoint, indexOfRoom);
  }
}
