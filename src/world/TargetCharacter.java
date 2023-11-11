package world;

import java.util.Objects;

/**
 * The target character.
 */
public class TargetCharacter implements Character {
  private Room room;
  private int index;
  private int health;
  private String name;

  /**
   * The constructor without parameter.
   */
  public TargetCharacter() {
    this.room = null;
    this.index = -1;
    this.health = 0;
    this.name = "";
  }

  /**
   * The constructor.
   *
   * @param room the initial room
   * @param name the name of character
   */
  public TargetCharacter(Room room, String name) {
    this.room = room;
    this.name = name;
    this.index = -1;
    this.room.addCharacter(this);
    this.health = 0;
  }

  @Override
  public Room getRoom() {
    return this.room;
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void setRoom(Room room) {
    this.room = room;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isComputer() {
    return true;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Target name: " + name);
    sb.append(", Current room: " + room.getName());
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
    TargetCharacter that = (TargetCharacter) o;
    return index == that.index && health == that.health && Objects.equals(room, that.room)
        && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(room, index, health, name);
  }
}
