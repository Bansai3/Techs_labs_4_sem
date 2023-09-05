package console.create_bank_commands;

/**
 * Class that provides with opportunity to define:
 * debit percent, credit commission percent, transfer commission
 * via console.
 */
public class DefinePercentsAndCommissionsCommand {
    public final double MaximalDebitAccountPercent = 15;
    public final double MinimalPercent = 0;
    public final double MaximalCreditCommissionPercent = 8;
    public final double MaximalTransferCommissionPercent = 5;
    private DefinePercentCommand mDefinePercentCommand;
    private DefineSumCommand mDefineSumCommand;

    public DefinePercentsAndCommissionsCommand() {
        mDefinePercentCommand = new DefinePercentCommand();
        mDefineSumCommand = new DefineSumCommand();
    }

    /**
     * Allows to define debit, credit commission, transfer commission percent via console.
     * @return array containing debit percent, credit commission percent, transfer commission percent.
     */
    public double[] definePercentsAndCommissionsCommand() {
        double debitPercent = 0;
        double creditCommissionPercent = 0;
        double transferCommission = 0;

        while(true) {
            System.out.println("Insert percent for debit account:");
            debitPercent = mDefinePercentCommand.definePercent();
            if (debitPercent > MaximalDebitAccountPercent || debitPercent < MinimalPercent) {
                System.out.println(
                        "Debit percent can not be > " + MaximalDebitAccountPercent + "% and < " + MinimalPercent + "%\n" +
                                "try again :)");
                continue;
            }
            break;
        }

        while(true) {
            System.out.println("Insert percent for credit commission:");
            creditCommissionPercent = mDefinePercentCommand.definePercent();
            if (creditCommissionPercent > MaximalCreditCommissionPercent || debitPercent < MinimalPercent) {
                System.out.println(
                        "Credit commission percent can not be > " + MaximalCreditCommissionPercent + "% and < " + MinimalPercent + "%\n" +
                                "try again :)");
                continue;
            }
            break;
        }

        while(true) {
            System.out.println("Insert percent for transfer commission:");
            transferCommission = mDefinePercentCommand.definePercent();
            if (transferCommission > MaximalTransferCommissionPercent || debitPercent < MinimalPercent) {
                System.out.println(
                        "Transfer commission percent can not be > " + MaximalTransferCommissionPercent + "% and < " + MinimalPercent + "%\n" +
                        "try again :)");
            continue;
            }
            break;
        }
        double[] numbers = {debitPercent, creditCommissionPercent, transferCommission};
        return numbers;
    }
}
