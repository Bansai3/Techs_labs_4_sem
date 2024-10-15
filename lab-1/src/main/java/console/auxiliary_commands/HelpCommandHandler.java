package console.auxiliary_commands;

import console.create_bank_commands.CreateBankCommandHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Class that implements help command of console interface.
 */
public class HelpCommandHandler extends CommandHandler {
    protected List<String> mCommands;

    public HelpCommandHandler() {
        mCommands = Arrays.asList(
                "help",
                "create_bank",
                "accrue_percents",
                "add_client",
                "open_debit_account",
                "open_deposit_account",
                "open_credit_account",
                "take_off",
                "top_up",
                "transfer",
                "cancel_transaction",
                "show_banks",
                "show_clients_of_bank",
                "show_client_accounts");
    }

    /**
     * Provides with add_client command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("help")) {
            System.out.println("Available operations:");
            int count = 1;
            for (String str : mCommands) {
                System.out.println(count++ + ") " + str);
            }
        } else {
            if (mNextHandler == null)
                mNextHandler = new CreateBankCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
