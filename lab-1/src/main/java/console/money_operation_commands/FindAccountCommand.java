package console.money_operation_commands;

import account.Account;
import bank.Bank;
import client.Client;
import console.add_client_commands.FindBankCommand;
import console.create_bank_commands.DefineSumCommand;
import console.open_account_commands.FindClientCommand;

import java.util.List;

/**
 * Class that provides with opportunity to find account via console.
 */
public class FindAccountCommand {

    public final int MinimalId = 0;
    private FindBankCommand mFindBankCommand;
    private FindClientCommand mFindClientCommand;
    private DefineSumCommand mDefineSumCommand;
    public FindAccountCommand() {
        mFindBankCommand = new FindBankCommand();
        mFindClientCommand = new FindClientCommand();
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     *
     * @param client client
     * @param bank
     * @return
     */
    public Account findAccount(Client client, Bank bank) {
        if (bank == null || client == null)
            return null;

        int accountId = MinimalId;
        List<Account> accounts = bank.getClientAccounts(client);
        while (true) {
            System.out.println("Insert account id(you can watch all information about accounts using command show_client_accounts)");
            accountId = mDefineSumCommand.defineSum();
            if (accountId < MinimalId) {
                System.out.println(
                        "Id must be >= " + MinimalId + "\n" +
                        "Try again :)");
                continue;
            }
            break;
        }

        for(Account account : accounts) {
            if (account.getId() == accountId)
                return account;
        }
        return null;
    }
}
