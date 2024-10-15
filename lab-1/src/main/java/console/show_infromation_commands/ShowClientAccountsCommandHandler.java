package console.show_infromation_commands;

import account.Account;
import bank.Bank;
import client.Client;
import console.auxiliary_commands.CommandHandler;
import console.add_client_commands.FindBankCommand;
import console.open_account_commands.FindClientCommand;

import java.util.List;

/**
 * Class that implements show_client_accounts command of console interface.
 */
public class ShowClientAccountsCommandHandler extends CommandHandler {

    private FindBankCommand mFindBankCommand;
    private FindClientCommand mFindClientCommand;

    public ShowClientAccountsCommandHandler() {
        mFindBankCommand = new FindBankCommand();
        mFindClientCommand = new FindClientCommand();
    }

    /**
     * Provides with show_client_accounts command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("show_client_accounts")) {
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            Client client = mFindClientCommand.findClient(bank);
            if (client == null || bank == null)
                return;
            List<Account> accounts = bank.getClientAccounts(client);
            System.out.println("AccountID:    AccountType:    AccountSum:");
            for (Account account : accounts) {
                System.out.printf("%d)    %s     %f%n", account.getId(), account.getClass(), account.getSum());
            }
        } else {
            if (mNextHandler == null)
                mNextHandler = new ShowClientsOfBankCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
