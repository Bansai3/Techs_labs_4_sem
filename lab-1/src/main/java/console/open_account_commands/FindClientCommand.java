package console.open_account_commands;

import bank.Bank;
import bank.CentralBank;
import client.Client;
import console.add_client_commands.FindBankCommand;
import console.create_bank_commands.DefineSumCommand;

/**
 * Class that provides with opportunity to find client via console.
 */
public class FindClientCommand {
    public final int MinimalId = 1;
    public final int AccountErrorNumber = -1;
    private DefineSumCommand mDefineSumCommand;

    public FindClientCommand() {
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     * Allows to find client of specific bank via console.
     *
     * @param bank bank where to find client.
     * @return object of client or null if client does not exist.
     */
    public Client findClient(Bank bank) {
        if (bank == null)
            return null;

        int id = MinimalId;
        while (true) {
            System.out.println("Insert client id(you can watch all information about clients using command show_clients_of_bank)");
            id = mDefineSumCommand.defineSum();
            if (id < MinimalId) {
                System.out.println(
                        "Id must be >= " + MinimalId + "\n" +
                                "Try again :)");
                continue;
            }
            break;
        }

        int clientId = id > bank.getClients().size() ? AccountErrorNumber : id;
        if (clientId == AccountErrorNumber) {
            System.out.println("There is no client with id you inserted in bank " + bank.getTitle());
            return null;
        }

        Client client = bank.getClient(clientId);
        return client;
    }
}
