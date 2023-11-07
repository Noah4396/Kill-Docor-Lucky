package world;

public class Pet implements Character{
  private Room room;
  private String name;
  @Override
  public Room getRoom() {
    return this.room;
  }

  @Override
  public int getIndex() {
    return 0;
  }

  @Override
  public void setRoom(Room room) {
    this.room = room;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean isComputer() {
    return false;
  }
}
