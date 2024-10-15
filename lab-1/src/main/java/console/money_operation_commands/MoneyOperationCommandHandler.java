package console.money_operation_commands;

import account.Account;
import bank.Bank;
import client.Client;
import console.auxiliary_commands.CommandHandler;
import console.add_client_commands.FindBankCommand;
import console.create_bank_commands.DefineSumCommand;
import console.open_account_commands.FindClientCommand;
import org.javatuples.Pair;

/**
 * Abstract MoneyOperationCommandHandler class.
 * Provides with objects of classes that are used to find clients, banks, accounts
 * and define sums for different operations.
 */
public abstract class MoneyOperationCommandHandler extends CommandHandler {
    public final int MinimalSum = 0;
    protected FindAccountCommand mFindAccountCommand;
    protected FindClientCommand mFindClientCommand;
    protected FindBankCommand mFindBankCommand;
    protected DefineSumCommand mDefineSumCommand;

    public MoneyOperationCommandHandler() {
        mFindAccountCommand = new FindAccountCommand();
        mFindClientCommand = new FindClientCommand();
        mFindBankCommand = new FindBankCommand();
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     * Allows to find bank and account via console.
     * @return pair of bank and account.
     */
    protected Pair<Bank, Account> getBankAccount() {
        Bank bank = mFindBankCommand.findBank(mCentralBank);
        Client client = mFindClientCommand.findClient(bank);
        Account account = mFindAccountCommand.findAccount(client, bank);
        return new Pair<>(bank, account);
    }
}
