package console.create_bank_commands;

import console.accrue_percents_commands.AccruePercentsCommandHandler;
import console.auxiliary_commands.CommandHandler;

import java.util.HashMap;

/**
 * Class that implements create_bank command of console interface.
 */
public class CreateBankCommandHandler extends CommandHandler {
    private DefineTitleCommand mDefineTitleCommand;
    private DefineDepositPercentsCommand mDefineDepositPercentsCommand;
    private DefinePercentsAndCommissionsCommand mDefinePercentsAndCommissionsCommand;
    private DefineSumsAndLimitsCommand mDefineSumsAndLimitsCommand;

    public CreateBankCommandHandler(){
        mDefineTitleCommand = new DefineTitleCommand();
        mDefineDepositPercentsCommand = new DefineDepositPercentsCommand();
        mDefinePercentsAndCommissionsCommand = new DefinePercentsAndCommissionsCommand();
        mDefineSumsAndLimitsCommand = new DefineSumsAndLimitsCommand();
    }

    /**
     * Provides with create_bank command implementation.
     * @param command command to execute.
     */
    @Override
    public void executeCommand(String command) {
        if (command.equals("create_bank")) {
            System.out.println("CREATING BANK:");
            HashMap<Integer, Double> depositPercents = new HashMap<>();
            double debitPercent = 0.0;
            double creditCommissionPercent = 0.0;
            double transferCommission = 0.0;
            int creditLimit = 0;
            int dubiousSum = 0;
            int depositAccountMinDurationInDays = 0;
            System.out.println("Insert title of the bank:");
            String title = mDefineTitleCommand.defineTitle();
            mDefineDepositPercentsCommand.defineDepositPercents(depositPercents);
            double[] percents = mDefinePercentsAndCommissionsCommand.definePercentsAndCommissionsCommand();
            int[] sums = mDefineSumsAndLimitsCommand.defineSumsAndLimits();
            debitPercent = percents[0];
            creditCommissionPercent = percents[1];
            transferCommission = percents[2];
            creditLimit = sums[0];
            dubiousSum = sums[1];
            depositAccountMinDurationInDays = sums[2];
            System.out.println("Creating bank...");
            mCentralBank.registerBank(depositPercents, debitPercent, creditCommissionPercent, creditLimit, transferCommission, dubiousSum, depositAccountMinDurationInDays, title);
            System.out.println("Bank created!");
        } else {
            if (mNextHandler == null)
                mNextHandler = new AccruePercentsCommandHandler();
            mNextHandler.executeCommand(command);
        }
    }
}
