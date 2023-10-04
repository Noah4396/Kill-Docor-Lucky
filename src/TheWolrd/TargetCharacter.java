package TheWolrd;
/**
 * The target character.
 */
public class TargetCharacter implements Character{
  private Room room;

  public TargetCharacter() {
    room = null;
  }

  public TargetCharacter(Room room) {
    this.room = room;
    room.addCharacter(this);
  }
  @Override
  public Room getRoom() {
    return room;
  }
}
