package console.open_account_commands;

import console.auxiliary_commands.CommandHandler;
import console.add_client_commands.DefineNotificationsCommand;
import console.add_client_commands.FindBankCommand;
import console.create_bank_commands.DefineSumCommand;

/**
 * Abstract OpenAccountCommandHandler class.
 * Provides with objects of classes that are used to find clients, banks,
 * define sums and notification status for accounts.
 */
public abstract class OpenAccountCommandHandler extends CommandHandler {
    protected FindClientCommand mFindClientCommand;
    protected FindBankCommand mFindBankCommand;
    protected DefineNotificationsCommand mDefineNotificationsCommand;

    protected DefineSumCommand mDefineSumCommand;

    public OpenAccountCommandHandler() {
        mFindClientCommand = new FindClientCommand();
        mFindBankCommand = new FindBankCommand();
        mDefineSumCommand = new DefineSumCommand();
        mDefineNotificationsCommand = new DefineNotificationsCommand();
    }
}
