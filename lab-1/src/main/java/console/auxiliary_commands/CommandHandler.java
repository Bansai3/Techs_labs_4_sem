package console.auxiliary_commands;

import bank.CentralBank;

/**
 * Abstract CommandHandler class.
 * Base class for all classes that handle console interface commands.
 */
public abstract class CommandHandler {
    protected static CentralBank mCentralBank;
    protected CommandHandler mNextHandler;

    public CommandHandler() {
        if (mCentralBank == null)
            mCentralBank = CentralBank.getCentralBankInstance();
    }

    public abstract void executeCommand(String command);
}
