package controller;
import java.util.Scanner;
public class Driver {
  public static void main(String[] args) {
    // Press Opt+Enter with your caret at the highlighted text to see how
    // IntelliJ IDEA suggests fixing it.
    boolean flag = true;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the world specification file path:");
    String path = scanner.nextLine();
    GamingConsole gamingConsole = new GamingConsole(path);
    System.out.println("Mansion load successfully");
    System.out.println(gamingConsole);
    // Press Ctrl+R or click the green arrow button in the gutter to run the code.
    while(flag){
      System.out.println("Select option: ");
      System.out.println("1.Move target");
      System.out.println("2.Output image");
      System.out.println("3.Display rooms by index");
      System.out.println("4.Neighbours by index");
      System.out.println("5. Visible rooms by index");
      System.out.println("6. Exit");
      int choice = scanner.nextInt();
      switch (choice){
        case 1:
          gamingConsole.moveTarget();
          System.out.println("The status of Target Character: " + gamingConsole.getDoctorLucky());
          break;
        case 2:
          gamingConsole.outputImage();
          System.out.println("The output image is res/example.png");
          break;
        case 3:
          System.out.println("All details of the rooms are displayed as follow:");
          gamingConsole.displayRooms();
          break;
        case 4:
          gamingConsole.displayNeighbours();
          break;
        case 5:
          gamingConsole.displayVisibleRooms();
          break;
        case 6:
          return;
        default:
          continue;
      }
    }
  }
}
