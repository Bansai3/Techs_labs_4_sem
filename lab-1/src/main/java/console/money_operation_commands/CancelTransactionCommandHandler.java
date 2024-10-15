package console.money_operation_commands;

import account.Account;
import bank.Bank;
import console.show_infromation_commands.ShowBanksCommandHandler;
import org.javatuples.Pair;
import transaction.Transaction;

/**
 * Class that implements cancel_transaction command of console interface.
 */
public class CancelTransactionCommandHandler extends MoneyOperationCommandHandler {
    /**
     * Provides with cancel_transaction command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("cancel_transaction")) {
            System.out.println("CANCELLING TRANSACTION:");
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

            Transaction transaction = bankAccount1.getValue1().findTransaction(bankAccount2.getValue1());
            if (transaction == null) {
                System.out.println("There is no transaction to cancel!");
                return;
            }
            System.out.println("Canceling transfer...");
            try {
                bankAccount1.getValue0().cancelTransaction(
                        bankAccount1.getValue1(), transaction);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return;
            }

            System.out.println("Transfer canceled!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new ShowBanksCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
