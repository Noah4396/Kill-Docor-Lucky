package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * The player character.
 */
public class PlayerCharacter implements Character {
  private Room room;
  private String name;
  private int index;
  private int itemCapacity;
  private int itemNumber;
  private ArrayList<Item> items;
  private boolean isComputer;

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name         is the name of the object.
   * @param index        is the index of the character.
   * @param itemCapacity the capacity of carrying items
   */
  public PlayerCharacter(String name, int index, int itemCapacity) {
    this.isComputer = false;
    this.room = null;
    this.index = index;
    this.name = name;
    this.itemCapacity = itemCapacity;
    this.itemNumber = 0;
    this.items = new ArrayList<>();
  }

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name         is the name of the object.
   * @param room         is the room the character stands in.
   * @param index        is the index of the character.
   * @param itemCapacity the capacity of carrying items
   */
  public PlayerCharacter(String name, Room room, int index, int itemCapacity) {
    this.isComputer = false;
    this.room = room;
    this.index = index;
    this.name = name;
    this.room.addCharacter(this);
    this.itemCapacity = itemCapacity;
    this.itemNumber = 0;
    this.items = new ArrayList<>();
  }

  /**
   * Get the item capacity.
   *
   * @return the item capacity.
   */
  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public Room getRoom() {
    return room;
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void setRoom(Room room) {
    this.room = room;
  }

  /**
   * Set the player as a computer controlled.
   */
  public void setAsComputer() {
    this.isComputer = true;
  }

  /**
   * Sort the items.
   */
  public void sortItems() {
    items.sort(Collections.reverseOrder());
  }

  /**
   * Get the item list.
   *
   * @return the item list.
   */
  public boolean hasItem() {
    return !items.isEmpty();
  }

  /**
   * Set the player as a human controlled.
   */
  public void setAsHumanControl() {
    this.isComputer = false;
  }

  /**
   * Get the item capacity.
   *
   * @return the item capacity.
   */
  public int getItemNumber() {
    return items.size();
  }

  @Override
  public boolean isComputer() {
    return this.isComputer;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayerCharacter character = (PlayerCharacter) o;
    return index == character.index && itemCapacity == character.itemCapacity
        && itemNumber == character.itemNumber && Objects.equals(name, character.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, index, itemCapacity, itemNumber);
  }

  /**
   * Check if the player is able to pick item.
   *
   * @return if the player is able to pick item.
   */
  public boolean isAbleToPick() {
    return itemNumber < itemCapacity;
  }

  /**
   * Pick an item.
   *
   * @param item is the item.
   */
  public void pickItem(Item item) {
    this.items.add(item);
    sortItems();
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Character name: " + name + "\n");
    sb.append("capacity: " + itemCapacity + "\n");
    sb.append("Current room: " + room.getName() + "\n");
    sb.append("index: " + index + "\n");
    sb.append("items: " + items + "\n");
    sb.append("player type: ");
    if (isComputer) {
      sb.append("computer");
    } else {
      sb.append("human");
    }
    return sb.toString();
  }

  /**
   * Display the items.
   *
   * @return the items.
   */
  public String displayItems() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < items.size(); i++) {
      sb.append("Index: " + i + ", ");
      sb.append(items.get(i).toString() + "\n");
    }
    return sb.toString();
  }

  /**
   * Use an item.
   *
   * @param index is the index of the item.
   * @return the damage point of the item.
   */
  public int useItem(int index) {
    if (index < 0 || index >= items.size()) {
      throw new IllegalArgumentException("Invalid index.");
    }
    Item item = items.get(index);
    items.remove(index);
    return item.getDamagePoint();
  }
}
