package console.money_operation_commands;

import account.Account;
import bank.Bank;
import org.javatuples.Pair;

/**
 * Class that implements transfer command of console interface.
 */

public class TransferCommandHandler extends MoneyOperationCommandHandler {
    /**
     * Provides with transfer command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("transfer")) {
            System.out.println("TRANSFERRING MONEY TO ACCOUNT:");

            Pair<Bank, Account> bankAccount1 = getBankAccount();
            if (bankAccount1.getValue0() == null || bankAccount1.getValue1() == null)
                return;

            Pair<Bank, Account> bankAccount2 = getBankAccount();
            if (bankAccount2.getValue0() == null || bankAccount2.getValue1() == null)
                return;
            int sum = MinimalSum;
            while (true) {
                System.out.println("Insert sum to transfer:");
                sum = mDefineSumCommand.defineSum();
                if (sum < MinimalSum) {
                    System.out.println(
                            "Sum to transfer must be > " + MinimalSum + "\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            System.out.println("Transferring...");

            try {
                bankAccount1.getValue0().transferMoneyToAnotherAccount(
                        bankAccount2.getValue0(), bankAccount1.getValue1(), bankAccount2.getValue1(), sum);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return;
            }

            System.out.println("Transferred!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new CancelTransactionCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
