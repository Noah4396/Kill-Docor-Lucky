package TheWolrd;

public class PlayerCharacter implements Character{
  private Room room;

  public PlayerCharacter() {
  }

  public PlayerCharacter(Room room) {
    this.room = room;
    this.room.addCharacter(this);
  }

  @Override
  public Room getRoom() {
    return room;
  }
}
