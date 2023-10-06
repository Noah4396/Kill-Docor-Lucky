package TheWolrd;

public class PlayerCharacter implements Character{
  private Room room;
  private String name;
  private int index;

  public PlayerCharacter(String name) {
    this.name = name;
  }

  public PlayerCharacter(String name, Room room) {
    this.room = room;
    this.room.addCharacter(this);
    this.name = name;
  }
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
}
