package console.add_client_commands;

import bank.Bank;
import bank.CentralBank;
import console.create_bank_commands.DefineSumCommand;

/**
 * Class that provides with opportunity to find bank via console.
 */
public class FindBankCommand {
    public final int DEFAULT_ID_VALUE = 0;
    public final int BANK_ERROR_NUMBER = -1;
    public final int MINIMAL_ID = 0;
    private DefineSumCommand mDefineSumCommand;

    public FindBankCommand() {
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     * Allows to find bank or ensure that bank does not exist via console.
     *
     * @param centralBank central bank
     * @return bank or null if bank does not exist.
     */
    public Bank findBank(CentralBank centralBank) {
        int bankId = DEFAULT_ID_VALUE;
        int id = DEFAULT_ID_VALUE;
        while (true) {
            System.out.println("Insert bank id(you can watch all ids and titles using command show_banks)");
            id = mDefineSumCommand.defineSum();
            if (id < MINIMAL_ID) {
                System.out.println(
                        "Id must be >= " + MINIMAL_ID + "\n" +
                                "Try again :)");
                continue;
            }
            break;
        }

        bankId = id < centralBank.getBanks().size() ? id : BANK_ERROR_NUMBER;
        if (bankId == BANK_ERROR_NUMBER) {
            System.out.println("There is no bank with id you inserted");
            return null;
        }

        Bank bank = centralBank.getBankById(bankId);
        return bank;
    }
}
