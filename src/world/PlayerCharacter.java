package world;

import java.util.ArrayList;
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

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name  is the name of the object.
   * @param room  is the room the character stands in.
   * @param index is the index of the character.
   */
  public PlayerCharacter(String name, Room room, int index, int itemCapacity) {
    this.room = room;
    this.index = index;
    this.name = name;
    this.room.addCharacter(this);
    this.itemCapacity = itemCapacity;
    this.itemNumber = 0;
    this.items = new ArrayList<>();
  }

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

  public boolean equals(Character o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return this.name.equals(o.getName());
  }

  @Override
  public String getName(){
    return this.name;
  }
  @Override
  public int hashCode() {
    return Objects.hash(room, name, index);
  }

  public boolean isAbleToPick(){
    return itemNumber < itemCapacity;
  }

  public void pickItem(Item item){
    this.items.add(item);
  }
}
