package console.create_bank_commands;

/**
 * Class that provides with opportunity to define:
 * credit limit, dubious sum, deposit account minimal duration in days
 * via console.
 */
public class DefineSumsAndLimitsCommand {

    public final int MinimalCreditLimitSum = 100000;
    public final int MaximalCreditLimitSum = 500000;
    public final int MinimalDubiousSum = 5000;
    public final int MaximalDubiousSum = 10000;
    public final int MinimalDepositAccountMinDurationInDays = 30;
    public final int MaximalDepositAccountMinDurationInDays = 730;
    private DefineSumCommand mDefineSumCommand;

    public DefineSumsAndLimitsCommand() {
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     * Allows to define credit limit, dubious Sum, deposit account minimal duration in days via console.
     *
     * @return array containing credit limit, dubious Sum, deposit account minimal duration in days.
     */
    public int[] defineSumsAndLimits() {
        int creditLimit = 0;
        int dubiousSum = 0;
        int depositAccountMinDurationInDays = 0;

        while (true) {
            System.out.println("Insert credit limit sum:");
            creditLimit = mDefineSumCommand.defineSum();
            if (creditLimit < MinimalCreditLimitSum || creditLimit > MaximalCreditLimitSum) {
                System.out.println(
                        "Credit sum must be >= " + MinimalCreditLimitSum + "and <= " + MaximalCreditLimitSum + "\n" +
                                "try again");
                continue;
            }
            break;
        }
        while (true) {
            System.out.println("Insert dubious sum:");
            dubiousSum = mDefineSumCommand.defineSum();
            if (dubiousSum < MinimalDubiousSum || dubiousSum > MaximalDubiousSum) {
                System.out.println(
                        "Dubious sum must be >= " + MinimalDubiousSum + "and <= " + MaximalDubiousSum + "\n" +
                                "try again :)");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Insert deposit account minimal duration in days:");
            depositAccountMinDurationInDays = mDefineSumCommand.defineSum();
            if (depositAccountMinDurationInDays < MinimalDepositAccountMinDurationInDays ||
                    depositAccountMinDurationInDays > MaximalDepositAccountMinDurationInDays) {
                System.out.println(
                        "Deposit account minimal duration in days must be >= " + MinimalDepositAccountMinDurationInDays +
                                "and <= " + MaximalDepositAccountMinDurationInDays + "\n" +
                                "try again :)");
                continue;
            }
            break;
        }
        int[] numbers = {creditLimit, dubiousSum, depositAccountMinDurationInDays};
        return numbers;
    }
}
