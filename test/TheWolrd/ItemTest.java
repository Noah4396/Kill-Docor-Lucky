package TheWolrd;

import TheWolrd.Item;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ItemTest {

  private Item item;

  @Before
  public void setUp() {
    // Initialize an Item object for testing
    item = new Item("Test Item", 5, 1);
  }

  @Test
  public void testGetName() {
    // Check if the getName method returns the correct name
    assertEquals("Test Item", item.getName());
  }

  @Test
  public void testGetDamagePoint() {
    // Check if the getDamagePoint method returns the correct damage point
    assertEquals(5, item.getDamagePoint());
  }

  @Test
  public void testGetIndexOfRoom() {
    // Check if the getIndexOfRoom method returns the correct index of the room
    assertEquals(1, item.getIndexOfRoom());
  }

  @Test
  public void testIsLargerThan() {
    Item otherItem = new Item("Other Item", 3, 1);

    // Check if the isLargerThan method correctly compares damage points
    assertTrue(item.isLargerThan(otherItem));
  }

  @Test
  public void testIsNotLargerThan() {
    Item otherItem = new Item("Other Item", 8, 1);

    // Check if the isLargerThan method correctly compares damage points
    assertFalse(item.isLargerThan(otherItem));
  }

  @Test
  public void testToString() {
    // Check if the toString method returns the expected string representation
    assertEquals("Item{name='Test Item', damagePoint=5, indexOfRoom=1}", item.toString());
  }

  // You can add more test methods to cover other behaviors of the Item class

}
