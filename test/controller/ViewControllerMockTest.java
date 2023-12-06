package controller;
import model.MockModel;
import model.Model;
import org.junit.Before;
import view.MockView;
import view.View;

public class ViewControllerMockTest {
  private Model mockModel;
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  private View mockView;

  /**
   * Set up.
   */
  @Before
  public void setUp() {
    modelLog = new StringBuilder();
    mockModel = new MockModel("res/mansion.txt", 5, modelLog, "12341234");
    viewLog = new StringBuilder();
    mockView = new MockView(viewLog, "12341234");
  }
}
