package console.money_operation_commands;

import account.Account;
import bank.Bank;
import client.Client;

/**
 * Class that implements top_up command of console interface.
 */

public class TopUpCommandHandler extends MoneyOperationCommandHandler {
    /**
     * Provides with top_up command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("top_up")) {
            System.out.println("TOPPING-UP MONEY TO ACCOUNT:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            Account account = mFindAccountCommand.findAccount(client, bank);
            if (account == null)
                return;
            int sum = MinimalSum;
            while (true) {
                System.out.println("Insert sum to top up:");
                sum = mDefineSumCommand.defineSum();
                if (sum < MinimalSum) {
                    System.out.println(
                            "Sum to take off must be > " + MinimalSum + "\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            System.out.println("Topping up sum...");
            try {
                account.topUp(sum);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.println("Sum topped up!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new TransferCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
