package world;
/**
 * Item in the rooms
 */
public class Item {
  private String name;
  private int damagePoint;
  private int indexOfRoom;

  public Item(String name, int damagePoint, int indexOfRoom) {
    this.name = name;
    this.damagePoint = damagePoint;
    this.indexOfRoom = indexOfRoom;
  }

  public String getName() {
    return name;
  }

  public int getDamagePoint() {
    return damagePoint;
  }

  public int getIndexOfRoom() {
    return indexOfRoom;
  }

  public boolean isLargerThan(Item item){
    return damagePoint > item.getDamagePoint();
  }

  @Override
  public String toString() {
    return "Item{" + "name='" + name + '\'' + ", damagePoint=" + damagePoint + ", indexOfRoom="
        + indexOfRoom + '}';
  }
}
