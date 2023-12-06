package view;

import controller.Features;
import model.ReadOnlyModel;
import world.PlayerCharacter;

public class MockView implements View{
  private StringBuilder log;
  private final String uniqueCode;

  public MockView(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void addClickListener(Features listener) {

  }

  @Override
  public void addKeyListener(Features listener) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void setMenuFeatures(Features features) {

  }

  @Override
  public void setPanelFeatures(Features features) {

  }

  @Override
  public void setModel(ReadOnlyModel model) {

  }

  @Override
  public void paintLayout() {

  }

  @Override
  public void addPlayer(PlayerCharacter player) {

  }
}
