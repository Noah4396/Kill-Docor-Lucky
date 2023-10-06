package world;

import java.util.ArrayList;

/**
 * Rooms represent the rooms in the game.
 */
public interface Room {


  /**
   * @Return true if it has another room on the left and
   * one people can enter the left room from this room.
   */
  public boolean hasLeftRoom();

  /**
   * @Return true if it has another room on the right and
   * one people can enter the right room from this room.
   */
  public boolean hasRightRoom();

  /**
   * @Return true if it has another room on the upper side and
   * one people can enter the upper room from this room.
   */
  public boolean hasUpperRoom();

  /**
   * @Return true if it has another room below it and
   * one people can enter the lower room from this room.
   */
  public boolean hasLowerRoom();
  /**
   * Add character to the room.
   */
  public void addCharacter(Character character);
  /**
   * Remove character of the room.
   */
  public void removeCharacter(Character character);
  /**
   * @Return true if a room is the neighbour.
   */
  public int isNeighbour(Room room);
  /**
   * Get name.
   */
  public String getName();
  /**
   * Get index.
   */
  public int getIndex();
  /**
   * Get number of neighbours.
   */
  public int getNumOfNeighbours();
  /**
   * Add item.
   */
  public void addItem(Item item);
  /**
   * Get the left corner.
   */
  public int getLeftCorner();
  /**
   * Get the right corner.
   */
  public int getRightCorner();
  /**
   * Get the upper corner.
   */
  public int getUpperCorner();
  /**
   * Get the lower corner.
   */
  public int getLowerCorner();
  /**
   * Add left room.
   */
  public void addLeftRoom(Room room);
  /**
   * Add right room
   */
  public void addRightRoom(Room room);
  /**
   * Add upper room.
   */
  public void addUpperRoom(Room room);
  /**
   * Add lower room.
   */
  public void addLowerRoom(Room room);
  /**
   * Set the visible rooms fo this room.
   */
  public void setVisibleRooms();
  /**
   * Get the neighbours.
   */
  public ArrayList<Room> getNeighbours(int index);
  /**
   * Display all the neighbours.
   */
  public String displayNeighbours();
  /**
   * Display all the vidible rooms.
   */
  public String displayVisibleRooms();
}
