package world;

/**
 * The character.
 */

public interface Character {
  public Room getRoom();

  public int getIndex();

  public void setRoom(Room room);

  public String getName();

  /**
   * Check if the character is computer controlled.
   */
  public boolean isComputer();
}
