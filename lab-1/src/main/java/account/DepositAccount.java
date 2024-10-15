package account;

import transaction.Transaction;

import java.util.ArrayList;

/**
 * Deposit account class.
 */
public class DepositAccount extends Account {
    private double mPercent;
    private int mDuration;
    private double mPercentSum;

    /**
     * Constructs the object of DepositAccount class.
     *
     * @param percent        year percent that is accrued on {@link DepositAccount#mSum}.
     * @param id             identifier of deposit account.
     * @param durationInDays duration of deposit account in days.
     * @param sum            sum to put on deposit account.
     */
    public DepositAccount(double percent, int id, int durationInDays, double sum) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkPercent(percent) == false)
            throw new IllegalArgumentException("Invalid percent!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkDuration(durationInDays) == false)
            throw new IllegalArgumentException("Invalid duration in days!");
        mId = id;
        mPercent = percent / 365;
        mDuration = durationInDays;
        mSum = sum;
        mPercentSum = 0;
        mTransactions = new ArrayList<>();

    }

    @Override
    public void takeOff(double sum) {
        if (mDuration != 0)
            return;
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (mSum - sum < 0)
            throw new ArithmeticException("Current sum is less than the sum to takeoff!");
        mSum -= sum;
    }

    @Override
    public void topUp(double sum) {
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        mSum += sum;
    }

    @Override
    public void accruePercent() {
        if (mDuration == 0)
            return;
        mPercentSum += mPercentSum / 100 * mSum;
        mDuration--;
    }

    @Override
    public void accruePercentSum() {
        if (mDuration == 0)
            return;
        mSum += mPercentSum;
        mPercentSum = 0;
    }

    @Override
    public Transaction transfer(double sum, Account account, double commission) {
        if (mDuration != 0)
            return null;
        validateTransfer(sum, account, commission);
        takeOff(sum + commission);
        account.topUp(sum);
        Transaction transaction = new Transaction(account, sum, commission);
        mTransactions.add(transaction);
        return transaction;
    }

    /**
     * Provides with percent.
     *
     * @return percent that is accrued on {@link DepositAccount#mPercentSum} every day.
     */
    public double getPercent() {
        return mPercent;
    }

    /**
     * Provides with duration in days.
     *
     * @return duration of deposit account in days.
     */

    public int getDuration() {
        return mDuration;
    }

    /**
     * Provides with percent sum.
     *
     * @return percent sum that is accrued on {@link DepositAccount#mSum} every month.
     */

    public double getPercentSum() {
        return mPercentSum;
    }

    private boolean checkPercent(double percent) {
        return percent > 0;
    }

    private boolean checkDuration(int durationInDays) {
        return durationInDays > 0;
    }

    private boolean checkId(int id) {
        return id > 0;
    }
}
