package account;

import transaction.Transaction;

import java.util.ArrayList;

/**
 * Debit account class.
 */

public class DebitAccount extends Account {
    private double mPercent;
    private double mPercentSum;

    /**
     * Constructs the object of DebitAccount class.
     *
     * @param percent year percent that is accrued on {@link DebitAccount#mSum}.
     * @param id      identifier of debit account.
     * @param sum     sum to put on debit account.
     */
    public DebitAccount(double percent, int id, double sum) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkPercent(percent) == false)
            throw new IllegalArgumentException("Invalid percent!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        mSum = sum;
        mId = id;
        mPercentSum = 0;
        mPercent = percent / 365;
        mTransactions = new ArrayList<>();
    }

    @Override
    public void takeOff(double sum) {
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
        mPercentSum += mPercent / 100 * mSum;
    }

    @Override
    public void accruePercentSum() {
        mSum += mPercentSum;
        mPercentSum = 0;
    }

    @Override
    public Transaction transfer(double sum, Account account, double commission) {
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
     * @return percent that is accrued on {@link DebitAccount#mPercentSum} every day.
     */
    public double getPercent() {
        return mPercent;
    }

    /**
     * Provides with percent sum.
     *
     * @return percent sum that is accrued on {@link DebitAccount#mSum} every month.
     */

    public double getPercentSum() {
        return mPercentSum;
    }

    private boolean checkPercent(double percent) {
        return percent > 0;
    }

    private boolean checkId(int id) {
        return id > 0;
    }
}
