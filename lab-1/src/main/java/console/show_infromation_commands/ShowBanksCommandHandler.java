package console.show_infromation_commands;

import bank.Bank;
import console.auxiliary_commands.CommandHandler;;

/**
 * Class that implements show_banks command of console interface.
 */
public class ShowBanksCommandHandler extends CommandHandler {
    /**
     * Provides with show_banks command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("show_banks")) {
            int count = 0;
            System.out.println("Id:       Titles:");
            for (Bank bank : mCentralBank.getBanks()) {
                System.out.println(count + ") " + bank.getTitle());
            }
        } else {
            if (mNextHandler == null)
                mNextHandler = new ShowClientAccountsCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
