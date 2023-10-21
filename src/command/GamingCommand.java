package command;

import model.GamingModel;
import model.Model;

/**
 * A gaming command interface.
 */
public interface GamingCommand {
  /**
   * Execute the command.
   * @param m the gaming model.
   */
  void execute(Model m);
}
