package console.show_infromation_commands;

import bank.Bank;
import client.Client;
import console.auxiliary_commands.CommandHandler;
import console.add_client_commands.FindBankCommand;
import console.open_account_commands.FindClientCommand;

/**
 * Class that implements show_clients_of_bank command of console interface.
 */
public class ShowClientsOfBankCommandHandler extends CommandHandler {
    private FindClientCommand mFindClientCommand;
    private FindBankCommand mFindBankCommand;

    public ShowClientsOfBankCommandHandler() {
        mFindClientCommand = new FindClientCommand();
        mFindBankCommand = new FindBankCommand();
    }

    /**
     * Provides with show_clients_of_bank command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("show_clients_of_bank")) {
            Bank bank = mFindBankCommand.findBank(mCentralBank);
            if (bank == null)
                return;
            int count = 1;
            System.out.println("Client:    Name:    Surname:     Address:     " +
                    "PassportNumber:     Notifications:     Id:");
            for (Client client : bank.getClients()) {
                System.out.printf("%d) %s %s %s %s %b %d%n", count, client.getName(), client.getSurname(),
                        client.getAddress(), client.getPassportNumber(), client.getCommonNotificationsStatus(), client.getId());
            }
        } else {
            System.out.println("Invalid command!");
        }
    }
}
