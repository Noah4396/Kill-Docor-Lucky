package world;

import java.util.ArrayList;

/**
 * Rooms represent the rooms in the game.
 */
public interface Room {
  /**
   * Have left room or not.
   * @return true if it has another room on the left.
   */
  public boolean hasLeftRoom();

  /**
   * Have right room or not.
   * @return true if it has another room on the right.
   */
  public boolean hasRightRoom();

  /**
   * Have upper room or not.
   * @return true if it has another room on the upper side.
   */
  public boolean hasUpperRoom();

  /**
   * Have lower room or not.
   * @return true if it has another room below it.
   */
  public boolean hasLowerRoom();

  /**
   * Add character.
   * @param character added the room.
   */
  public void addCharacter(Character character);

  /**
   * Remove character.
   * @param character of the room.
   */
  public void removeCharacter(Character character);

  /**
   * Judge if is neighbour.
   * @param room another room
   * @return 1,2,3,4 corresponding to which direction is room is
   */
  public int isNeighbour(Room room);

  /**
   * Get the name.
   * @return the name
   */
  public String getName();

  /**
   * Get the index.
   * @return the index
   */
  public int getIndex();

  /**
   * Get number of neighbours.
   * @return number of neighbours.
   */
  public int getNumOfNeighbours();

  /**
   * Add item.
   * @param item add to the room
   */
  public void addItem(Item item);

  /**
   * Get left corner.
   * @return the left corner
   */
  public int getLeftCorner();

  /**
   * Get right corner.
   * @return the right corner.
   */
  public int getRightCorner();

  /**
   * Get upper corner.
   * @return the upper corner.
   */
  public int getUpperCorner();

  /**
   * Get lower corner.
   * @return the lower corner.
   */
  public int getLowerCorner();

  /**
   * Add left room.
   * @param room left room.
   */
  public void addLeftRoom(Room room);

  /**
   * Add right room.
   * @param room right room
   */
  public void addRightRoom(Room room);

  /**
   * Add upper room.
   * @param room upper room.
   */
  public void addUpperRoom(Room room);

  /**
   * Add lower room.
   * @param room lower room.
   */
  public void addLowerRoom(Room room);

  /**
   * Set the visible rooms fo this room.
   */
  public void setVisibleRooms();

  /**
   * Get neighbours.
   * @param index of neighbours.
   * @return the neighbours.
   */
  public ArrayList<Room> getNeighbours(int index);

  /**
   * Display neighbours.
   * @param direction the direction of the neighbours.
   * @return all the neighbours.
   */
  public String displayNeighbours(int direction);

  /**
   * Display visible rooms.
   * @return all the visible rooms.
   */
  public String displayVisibleRooms();

  /**
   * Get the number of items left in this room.
   */
  public int getItemsNumber();

  /**
   * Get an item of the index.
   */
  public Item deleteItem(int index);

  /**
   * Get the neighbour of direction and index.
   * @param direction stands for the direction
   * @param index stands for the index of the neighbour
   */
  public Room getNeighbour(int direction, int index);
}
