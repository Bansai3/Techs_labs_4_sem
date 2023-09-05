package account;

import transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract account class.
 */
public abstract class Account {
    protected int mId;

    protected ArrayList<Transaction> mTransactions;

    protected double mSum;

    /**
     * Takes off money from account.
     *
     * @param sum money to take off.
     */
    public abstract void takeOff(double sum);

    /**
     * Tops up account with money.
     *
     * @param sum money to top up account with.
     */
    public abstract void topUp(double sum);

    /**
     * Accrues percents.
     */
    public abstract void accruePercent();

    /**
     * Accrues percent sum in the end of month.
     */
    public abstract void accruePercentSum();

    /**
     * Transfers money from one account to another.
     *
     * @param sum        sum to transact from one account to another.
     * @param account    account to transfer sum.
     * @param commission commission sum.
     * @return object of Transaction class.
     */
    public abstract Transaction transfer(double sum, Account account, double commission);

    public int getId() {
        return mId;
    }

    public double getSum() {
        return mSum;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(mTransactions);
    }

    public Transaction findTransaction(Account anotherAccount) {
        Transaction transactionToFind = null;
        for(Transaction transaction : mTransactions) {
            if (transaction.getAccount() == anotherAccount)
                transactionToFind = transaction;
        }
        return transactionToFind;
    }

    protected boolean checkAccount(Account account) {
        return account != null;
    }

    protected boolean checkCommission(double commission) {
        return commission >= 0;
    }

    protected boolean checkSum(double sum) {
        return sum > 0;
    }

    /**
     * Validates transaction.
     *
     * @param sum        sum to transfer.
     * @param account    account to transfer sum.
     * @param commission commission sum.
     */
    protected void validateTransfer(double sum, Account account, double commission) {
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkAccount(account) == false)
            throw new IllegalArgumentException("Invalid account!");
        if (checkCommission(commission) == false)
            throw new IllegalArgumentException("Invalid commission!");
    }
}
