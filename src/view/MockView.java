package view;

import controller.Features;
import model.ReadOnlyModel;
import world.PlayerCharacter;

public class MockView implements View {
  private StringBuilder log;
  private final String uniqueCode;

  public MockView(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void addClickListener(Features listener) {
    log.append("addClickListener, input:" + listener.toString() + "\n");
  }

  @Override
  public void addKeyListener(Features listener) {
    log.append("addKeyListener, input:" + listener.toString() + "\n");
  }

  @Override
  public void refresh() {
    log.append("refresh\n");
  }

  @Override
  public void makeVisible() {
    log.append("makeVisible\n");
  }

  @Override
  public void setMenuFeatures(Features features) {
    log.append("setMenuFeatures, input:" + features.toString() + "\n");
  }

  @Override
  public void setPanelFeatures(Features features) {
    log.append("setPanelFeatures, input:" + features.toString() + "\n");
  }

  @Override
  public void setModel(ReadOnlyModel model) {
    log.append("setModel\n");
  }

  @Override
  public void paintLayout() {
    log.append("paintLayout\n");
  }

  @Override
  public void addPlayer(PlayerCharacter player) {
    log.append("addPlayer, input:" + player.toString() + "\n");
  }
}
