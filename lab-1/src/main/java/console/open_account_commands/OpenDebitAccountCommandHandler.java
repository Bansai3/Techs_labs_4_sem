package console.open_account_commands;

import bank.Bank;
import client.Client;

/**
 * Class that implements open_debit_account command of console interface.
 */
public class OpenDebitAccountCommandHandler extends OpenAccountCommandHandler {
    /**
     * Provides with open_debit_account command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("open_debit_account")) {
            System.out.println("DEBIT ACCOUNT OPENING:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            if (client == null || bank == null)
                return;
            System.out.println("Insert debit account sum:");
            double sum = mDefineSumCommand.defineSum();
            boolean getNotification = mDefineNotificationsCommand.defineNotifications();
            System.out.println("Opening debit account...");
            bank.openDebitAccount(client, sum, getNotification);
            System.out.println("Debit account created!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new OpenDepositAccountCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
