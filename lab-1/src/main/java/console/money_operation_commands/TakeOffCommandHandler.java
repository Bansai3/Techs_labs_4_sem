package console.money_operation_commands;

import account.Account;
import bank.Bank;
import client.Client;

/**
 * Class that implements take_off command of console interface.
 */
public class TakeOffCommandHandler extends MoneyOperationCommandHandler {
    /**
     * Provides with take_off command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("take_off")) {
            System.out.println("TAKING OFF MONEY FROM ACCOUNT:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            Account account = mFindAccountCommand.findAccount(client, bank);
            if (account == null)
                return;
            int sum = MinimalSum;
            while (true) {
                System.out.println("Insert sum to takeoff:");
                sum = mDefineSumCommand.defineSum();
                if (sum < MinimalSum) {
                    System.out.println(
                            "Sum to take off must be > " + MinimalSum + "\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            System.out.println("Taking off sum...");
            try {
                account.takeOff(sum);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.println("Sum took off!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new TopUpCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
