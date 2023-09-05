package account;

import transaction.Transaction;

import java.util.ArrayList;

/**
 * Credit account class.
 */
public class CreditAccount extends Account {
    private double mCommissionPercent;
    private double mCommissionSum;

    /**
     * Constructs the object of CreditAccount class.
     *
     * @param sum               money to put on credit account.
     * @param commissionPercent commission for exploitation when sum is negative.
     * @param id                credit account identifier.
     */
    public CreditAccount(double sum, double commissionPercent, int id) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum");
        if (checkCommissionPercent(commissionPercent) == false)
            throw new IllegalArgumentException("Invalid commission percent!");
        mSum = sum;
        mCommissionPercent = commissionPercent;
        mId = id;
        mCommissionSum = 0;
        mTransactions = new ArrayList<>();
    }

    @Override
    public void takeOff(double sum) {
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
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
        if (mSum >= 0)
            return;
        mCommissionSum += Math.abs(mCommissionPercent / 100 * mSum);
    }

    @Override
    public void accruePercentSum() {
        mSum -= mCommissionSum;
        mCommissionSum = 0;
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
     * Provides with commission percent.
     *
     * @return commission percent that is accrued when {@link CreditAccount#mSum} is negative.
     */
    public double getCommissionPercent() {
        return mCommissionPercent;
    }

    /**
     * Provides with commission sum.
     *
     * @return sum that is subtracted every month if {@link CreditAccount#mSum} was negative.
     */
    public double getCommissionSum() {
        return mCommissionSum;
    }

    private boolean checkCommissionPercent(double commissionPercent) {
        return commissionPercent > 0;
    }

    private boolean checkId(int id) {
        return id > 0;
    }
}
