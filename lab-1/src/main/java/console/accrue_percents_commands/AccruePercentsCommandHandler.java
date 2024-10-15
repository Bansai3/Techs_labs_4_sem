package console.accrue_percents_commands;

import console.add_client_commands.AddClientCommandHandler;
import console.auxiliary_commands.CommandHandler;
import console.create_bank_commands.DefineSumCommand;

/**
 * Class that implements accrue_percents command of console interface.
 */
public class AccruePercentsCommandHandler extends CommandHandler {
    public final int MinimalDaysAmount = 1;
    private DefineSumCommand mDefineSum;

    public AccruePercentsCommandHandler() {
        mDefineSum = new DefineSumCommand();
    }

    /**
     * Provides with accrue_percents command implementation.
     *
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("accrue_percents")) {
            System.out.println("ACCRUING PERCENTS AND COMMISSIONS:");
            int daysAmount = 0;
            while (true) {
                System.out.println("Insert days amount during which percents and commissions will be accrued:");
                daysAmount = mDefineSum.defineSum();
                if (daysAmount < MinimalDaysAmount) {
                    System.out.println(
                            "Days amount must be >= " + MinimalDaysAmount + "days\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            mCentralBank.accrueCommissionAndPercents(daysAmount);
            System.out.println("Percents and commissions accrued!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new AddClientCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
