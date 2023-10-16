package controller;

import java.util.Scanner;

/**
 * The driver.
 */

public class Driver {

  /**
   * The main function.
   * @param args the arguments.
   */
  public static void main(String[] args) {
    // Press Opt+Enter with your caret at the highlighted text to see how
    // IntelliJ IDEA suggests fixing it.
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the world specification file path:");
    String path = scanner.nextLine();
    GamingModel gamingModel = new GamingModel(path, 100);
    System.out.println("Mansion load successfully");
    System.out.println(gamingModel);
    // Press Ctrl+R or click the green arrow button in the gutter to run the code.
    boolean flag = true;
    while (flag) {
      System.out.println("Select option: ");
      System.out.println("1.Move target");
      System.out.println("2.Output image");
      System.out.println("3.Display rooms by index");
      System.out.println("4.Neighbours by index");
      System.out.println("5. Visible rooms by index");
      System.out.println("6. Exit");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          gamingModel.moveTarget();
          System.out.println("The status of Target Character: " + gamingModel.getDoctorLucky());
          break;
        case 2:
          gamingModel.outputImage();
          System.out.println("The output image is res/example.png");
          break;
        case 3:
          System.out.println("All details of the rooms are displayed as follow:");
          gamingModel.displayRooms();
          break;
        case 4:
          System.out.println(gamingModel.displayNAllNeighbours());
          break;
        case 5:
          System.out.println(gamingModel.displayVisibleRooms());
          break;
        case 6:
          return;
        default:
          continue;
      }
    }
  }
}
