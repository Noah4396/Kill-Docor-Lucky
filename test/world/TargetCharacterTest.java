package world;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Test target Character.
 */
public class TargetCharacterTest {

  private TargetCharacter targetCharacter;
  private SpecifiedRoom specifiedRoom;
  /**
   * Initialize the tester.
   */
  @Before
  public void setUp() {
    // Initialize a TargetCharacter object for testing
    specifiedRoom = new SpecifiedRoom("Left Room", 2, -10, 0, 0, 10);
    targetCharacter = new TargetCharacter(specifiedRoom, "Test Target Character");
    //targetCharacter.setName("Test Target Character");
  }

  @Test
  public void testGetName() {
    // Check if the getName method returns the correct name
    assertEquals("Test Target Character", targetCharacter.getName());
  }

  @Test
  public void testGetRoom() {
    //targetCharacter.setRoom(specifiedRoom);
    assertEquals(specifiedRoom, targetCharacter.getRoom());
  }

  @Test
  public void testToString() {
    // Check if the toString method returns the expected string representation
    assertEquals("TargetCharacter{roomIndex=2, roomName = Left Room, index=-1, health=0"
        + ", name='Test Target Character'}", targetCharacter.toString());
  }

  // You can add more test methods to cover other behaviors of the TargetCharacter class

}
