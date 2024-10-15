package console.create_bank_commands;

import java.util.HashMap;

/**
 * Class that provides with opportunity to define deposit percents via console.
 */
public class DefineDepositPercentsCommand {
    public final int MinimalSum = 0;
    public final double MinimalPercent = 0;
    public final int MaximalDifferenceBetweenDepositAccountSums = 100000;
    public final double MaximalDifferenceBetweenDepositAccountPercents = 3;
    public final int AvailableDepositSumCount = 3;

    private DefineSumCommand mDefineSumCommand;
    private DefinePercentCommand mDefineDepositAccountPercentCommand;

    public DefineDepositPercentsCommand() {
        mDefineSumCommand = new DefineSumCommand();
        mDefineDepositAccountPercentCommand = new DefinePercentCommand();
    }

    /**
     * Allows to define deposit percents via console.
     *
     * @param depositPercents the collection of pairs (sum, sum percent).
     */
    public void defineDepositPercents(HashMap<Integer, Double> depositPercents) {
        System.out.println("Define deposit percents:");
        int currentSum = MinimalSum;
        double currentPercent = MinimalPercent;
        int sum;
        double percent;
        int i = 1;
        while (true) {
            while (true) {
                System.out.println("Insert " + i + " sum for deposit account:");
                sum = mDefineSumCommand.defineSum();
                if (sum <= currentSum || sum >= currentSum + MaximalDifferenceBetweenDepositAccountSums) {
                    System.out.println(
                            "Sum can not be <= prev sum and >= prev sum + " + MaximalDifferenceBetweenDepositAccountSums + "\n" +
                                    "Your prev sum: " + currentSum + "\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            currentSum = sum;
            while (true) {
                System.out.println("Insert percent for the " + i + " sum of deposit account:");
                percent = mDefineDepositAccountPercentCommand.definePercent();
                if (percent <= currentPercent || percent >= currentPercent + MaximalDifferenceBetweenDepositAccountPercents) {
                    System.out.println(
                            "Percent can not be <= prev percent and >= prev percent + " + MaximalDifferenceBetweenDepositAccountPercents + "\n" +
                                    "Your prev percent: " + currentPercent + "\n" +
                                    "try again :)");
                    continue;
                }
                break;
            }

            currentPercent = percent;
            depositPercents.put(currentSum, currentPercent);
            i++;
            if (i > AvailableDepositSumCount)
                break;
        }
    }
}
