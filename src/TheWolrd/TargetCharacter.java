package TheWolrd;
/**
 * The target character.
 */
public class TargetCharacter implements Character{
  private Room room;
  private int index;
  private int health;
  private String name;
  public TargetCharacter() {
    room = null;
    index = -1;
  }

  public TargetCharacter(Room room) {
    this.room = room;
    index = -1;
    this.room.addCharacter(this);
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

  public void setHealth(int health) {
    this.health = health;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
