package TheWolrd;

public class PlayerCharacter implements Character{
  private Room room;
  private String name;
  private int index;

  public PlayerCharacter() {
  }

  public PlayerCharacter(Room room) {
    this.room = room;
    this.room.addCharacter(this);
  }
  public PlayerCharacter(Room room, int index) {
    this.room = room;
    this.index = index;
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
}
