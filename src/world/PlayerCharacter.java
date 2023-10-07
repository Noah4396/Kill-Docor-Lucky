package world;

import java.util.Objects;

/**
 * The player character.
 */
public class PlayerCharacter implements Character {
  private Room room;
  private String name;
  private int index;

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name is the name of the object.
   */
  public PlayerCharacter(String name) {
    this.name = name;
    this.room = null;
    this.index = -1;
  }

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name is the name of the object.
   * @param room is the room the character stands in.
   */
  public PlayerCharacter(String name, Room room) {
    this.room = room;
    this.room.addCharacter(this);
    this.name = name;
    this.index = -1;
  }

  /**
   * Construct a PlayerCharacter class.
   *
   * @param name is the name of the object.
   * @param room is the room the character stands in.
   * @param index is the index of the character.
   */
  public PlayerCharacter(String name, Room room, int index) {
    this.room = room;
    this.index = index;
    this.name = name;
    this.room.addCharacter(this);
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

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    PlayerCharacter that = (PlayerCharacter) o;
    return index == that.index && Objects.equals(room, that.room) && Objects.equals(name,
        that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(room, name, index);
  }
}
