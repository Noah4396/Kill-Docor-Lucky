package TheWolrd;
/**
 * The target character.
 */
public class TargetCharacter implements Character{
  private Room room;
  private int index;
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
    return -1;
  }
  @Override
  public void setRoom(Room room) {
    this.room = room;
  }
}
