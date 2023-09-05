package console.open_account_commands;

import bank.Bank;
import client.Client;

/**
 * Class that implements open_deposit_account command of console interface.
 */
public class OpenDepositAccountCommandHandler extends OpenAccountCommandHandler {
    /**
     * Provides with open_deposit_account command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("open_deposit_account")) {
            System.out.println("DEPOSIT ACCOUNT OPENING:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            if (client == null || bank == null)
                return;
            System.out.println("Insert duration in days for deposit account:");
            int durationInDays = mDefineSumCommand.defineSum();
            System.out.println("Insert sum for deposit account:");
            double sum = mDefineSumCommand.defineSum();
            boolean getNotification = mDefineNotificationsCommand.defineNotifications();
            System.out.println("Opening deposit account...");
            bank.openDepositAccount(client, durationInDays, sum, getNotification);
            System.out.println("Deposit account created!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new OpenCreditAccountCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
