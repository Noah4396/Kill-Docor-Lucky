package command;

import model.GamingModel;

/**
 * A gaming command interface.
 */
public interface GamingCommand {
  /**
   * Execute the command.
   * @param m the gaming model.
   */
  void execute(GamingModel m);
}
