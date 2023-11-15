package world;

/**
 * The character.
 */
public interface Character {
  /**
   * Get the room the character stands in.
   * @return the room.
   */
  public Room getRoom();

  /**
   * Get the index of the character.
   * @return the index.
   */
  public int getIndex();

  /**
   * Set the room the character stands in.
   * @param room the room.
   */
  public void setRoom(Room room);

  /**
   * Get the name of the character.
   * @return the name.
   */
  public String getName();

  /**
   * Check if the character is computer controlled.
   * @return if it is computer.
   */
  public boolean isComputer();
}
