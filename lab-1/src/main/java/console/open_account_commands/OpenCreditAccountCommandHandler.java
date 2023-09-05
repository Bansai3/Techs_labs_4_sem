package console.open_account_commands;

import bank.Bank;
import client.Client;
import console.money_operation_commands.TakeOffCommandHandler;

/**
 * Class that implements open_credit_account command of console interface.
 */
public class OpenCreditAccountCommandHandler extends OpenAccountCommandHandler {
    public final int DEFAULT_SUM = 0;

    /**
     * Provides with open_credit_account command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if(command.equals("open_credit_account")) {
            System.out.println("CREDIT ACCOUNT OPENING:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            if (client == null || bank == null)
                return;
            int sum = DEFAULT_SUM;
            while(true) {
                System.out.println("Insert credit account sum:");
                sum = mDefineSumCommand.defineSum();
                if (sum > bank.getCreditLimit()) {
                    System.out.println("The sum is bigger than credit limit: " + bank.getCreditLimit() + "\n" +
                                       "Try again!");
                    continue;
                }
                break;
            }
            boolean getNotification = mDefineNotificationsCommand.defineNotifications();
            System.out.println("Opening credit account...");
            bank.openCreditAccount(client, sum, getNotification);
            System.out.println("Credit account created!");
        }
        else {
            if (mNextHandler == null)
                mNextHandler = new TakeOffCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
