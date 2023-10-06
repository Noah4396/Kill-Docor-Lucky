package TheWolrd;

import TheWolrd.Item;
import TheWolrd.PlayerCharacter;
import TheWolrd.SpecifiedRoom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecifiedRoomTest {

  private SpecifiedRoom room;

  @Before
  public void setUp() {
    // Initialize a SpecifiedRoom object for testing
    room = new SpecifiedRoom("Test Room", 1, 0, 10, 0, 10);
  }

  @Test
  public void testAddItem() {
    Item item = new Item("Test Item", 5, room.getIndex());
    room.addItem(item);

    // Check if the item was added to the room
    assertTrue(room.getItems().contains(item));
  }

  @Test
  public void testGetItem() {
    Item item = new Item("Test Item", 5, room.getIndex());
    room.addItem(item);

    // Get an item from the room
    Item retrievedItem = room.getItem();

    // Check if the retrieved item is the same as the added item
    assertEquals(item, retrievedItem);

    // Check that the item was removed from the room
    assertFalse(room.getItems().contains(item));
  }

  @Test
  public void testAddCharacter() {
    PlayerCharacter character = new PlayerCharacter("Test Character");
    room.addCharacter(character);

    // Check if the character was added to the room
    assertTrue(room.getCharacters().contains(character));
  }

  @Test
  public void testRemoveCharacter() {
    PlayerCharacter character = new PlayerCharacter("Test Character");
    room.addCharacter(character);
    room.removeCharacter(character);

    // Check that the character was removed from the room
    assertFalse(room.getCharacters().contains(character));
  }

  @Test
  public void testAddLeftRoom() {
    SpecifiedRoom leftRoom = new SpecifiedRoom("Left Room", 2, -10, 0, 0, 10);
    room.addLeftRoom(leftRoom);

    // Check if the left room was added to the room's leftRooms list
    assertTrue(room.getLeftRooms().contains(leftRoom));
  }

  @Test
  public void testAddRightRoom() {
    SpecifiedRoom rightRoom = new SpecifiedRoom("Right Room", 3, 10, 20, 0, 10);
    room.addRightRoom(rightRoom);

    // Check if the right room was added to the room's rightRooms list
    assertTrue(room.getRightRooms().contains(rightRoom));
  }

  @Test
  public void testAddUpperRoom() {
    SpecifiedRoom upperRoom = new SpecifiedRoom("Upper Room", 4, 0, 10, 10, 20);
    room.addUpperRoom(upperRoom);

    // Check if the upper room was added to the room's upperRooms list
    assertTrue(room.getUpperRooms().contains(upperRoom));
  }

  @Test
  public void testAddLowerRoom() {
    SpecifiedRoom lowerRoom = new SpecifiedRoom("Lower Room", 5, 0, 10, -10, 0);
    room.addLowerRoom(lowerRoom);

    // Check if the lower room was added to the room's lowerRooms list
    assertTrue(room.getLowerRooms().contains(lowerRoom));
  }

  // Placeholder for testing Character-related methods
  @Test
  public void testCharacterMethods() {
    // Implement tests for Character-related methods here
    // Assuming there are methods like addCharacter, removeCharacter, etc.
  }

  // You can add more test methods to cover other methods and behaviors of the SpecifiedRoom class

}
