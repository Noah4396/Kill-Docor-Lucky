package world;

/**
 * The pet.
 */
public class Pet implements Character {
  private Room room;
  private String name;
  private int direction;

  /**
   * The constructor.
   *
   * @param name is the name of the pet.
   */
  public Pet(String name) {
    this.room = null;
    this.name = name;
    this.direction = 0;
  }

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

  /**
   * Get the direction of the pet.
   *
   * @return the direction.
   */
  public int getDirection() {
    return direction;
  }

  /**
   * Set the direction of the pet.
   *
   * @param direction is the direction.
   */
  public void setDirection(int direction) {
    this.direction = direction;
  }

  @Override
  public boolean isComputer() {
    return false;
  }
}
