package console.add_client_commands;

import bank.Bank;
import console.auxiliary_commands.CommandHandler;
import console.create_bank_commands.DefineTitleCommand;
import console.open_account_commands.OpenDebitAccountCommandHandler;

/**
 * Class that implements add_client command of console interface.
 */
public class AddClientCommandHandler extends CommandHandler {
    private FindBankCommand mFindBankCommand;
    private DefineTitleCommand mDefineTitleCommand;
    private DefineAddressCommand mDefineAddressCommand;
    private DefinePassportNumberCommand mDefinePassportNumberCommand;
    private DefineNotificationsCommand mDefineNotificationsCommand;

    public AddClientCommandHandler() {
        mDefineTitleCommand = new DefineTitleCommand();
        mDefineAddressCommand = new DefineAddressCommand();
        mDefinePassportNumberCommand = new DefinePassportNumberCommand();
        mDefineNotificationsCommand = new DefineNotificationsCommand();
        mFindBankCommand = new FindBankCommand();
    }

    /**
     * Provides with add_client command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("add_client")) {
            System.out.println("ADD CLIENT:");
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            if (bank == null)
                return;

            System.out.println("Insert client's data:");
            System.out.println("Insert client's name:");
            String name = mDefineTitleCommand.defineTitle();
            System.out.println("Insert client's surname:");
            String surname = mDefineTitleCommand.defineTitle();
            String address = mDefineAddressCommand.defineAddress();
            String passportNumber = mDefinePassportNumberCommand.definePassportNumber();
            boolean getCommonNotifications = mDefineNotificationsCommand.defineNotifications();
            System.out.println("Adding client...");
            bank.addClient(name, surname, address, passportNumber, getCommonNotifications);
            System.out.println("Client added!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new OpenDebitAccountCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
