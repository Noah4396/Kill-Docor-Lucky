package world;

/**
 * The target character.
 */
public class TargetCharacter implements Character {
  private Room room;
  private int index;
  private int health;
  private String name;

  public TargetCharacter() {
    this.room = null;
    this.index = -1;
  }

  /**
   * The constructor.
   * @param room the initial room
   * @param name the name of character
   */
  public TargetCharacter(Room room, String name) {
    this.room = room;
    this.name = name;
    index = -1;
    this.room.addCharacter(this);
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "TargetCharacter{" + "roomIndex=" + room.getIndex() + ""
        + ", roomName = " + room.getName() + ", index=" + index + ", health="
        + health + ", name='" + name + '\'' + '}';
  }
}
