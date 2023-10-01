package TheWolrd;

/**
 * Rooms represent the rooms in the game.
 */
public interface Room {


  /**
   * Return true if it has another room on the left and
   * one people can enter the left room from this room.
   */
  public boolean hasLeftRoom();

  /**
   * Return true if it has another room on the right and
   * one people can enter the right room from this room.
   */
  public boolean hasRightRoom();

  /**
   * Return true if it has another room on the upper side and
   * one people can enter the upper room from this room.
   */
  public boolean hasUpperRoom();

  /**
   * Return true if it has another room below it and
   * one people can enter the lower room from this room.
   */
  public boolean hasLowerRoom();
}
