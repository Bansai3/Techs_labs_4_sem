package bank;

import lombok.Getter;

/**
 * Class that describes bank's characteristic parameters.
 */
@Getter
public class BankCharacteristicParameters {
    private double mDebitPercent;
    private double mCreditCommissionPercent;
    private double mTransferCommission;
    private int mDepositAccountMinDurationInDays;
    private int mCreditLimit;
    private int mDubiousSum;


    public BankCharacteristicParameters(
            double debitPercent,
            double creditCommissionPercent,
            double transferCommission,
            int depositAccountMinDurationInDays,
            int creditLimit,
            int dubiousSum) {
        if (checkPercent(debitPercent) == false)
            throw new IllegalArgumentException("Invalid debit percent!");
        if (checkPercent(creditCommissionPercent) == false)
            throw new IllegalArgumentException("Invalid commission percent!");
        if (checkCreditLimit(creditLimit) == false)
            throw new IllegalArgumentException("Invalid credit limit!");
        if (checkPercent(transferCommission) == false)
            throw new IllegalArgumentException("Invalid transfer commission!");
        if (checkDubiousSum(dubiousSum) == false)
            throw new IllegalArgumentException("Invalid dubious sum!");
        if (checkDuration(depositAccountMinDurationInDays) == false)
            throw new IllegalArgumentException("Invalid deposit account minimal duration!");
        mDebitPercent = debitPercent;
        mCreditCommissionPercent = creditCommissionPercent;
        mTransferCommission = transferCommission;
        mDepositAccountMinDurationInDays = depositAccountMinDurationInDays;
        mCreditLimit = creditLimit;
        mDubiousSum = dubiousSum;
    }

    public void setDebitPercent(double newDebitPercent) {
        if (checkPercent(newDebitPercent) == false)
            throw new IllegalArgumentException("Invalid debit percent!");
        mDebitPercent = newDebitPercent;
    }

    public void setCreditCommissionPercent(double newCreditCommissionPercent) {
        if (checkPercent(newCreditCommissionPercent) == false)
            throw new IllegalArgumentException("Invalid commission percent!");
        mCreditCommissionPercent = newCreditCommissionPercent;
    }

    public void setTransferCommission(double newTransferCommission) {
        if (checkPercent(newTransferCommission) == false)
            throw new IllegalArgumentException("Invalid transfer commission!");
        mTransferCommission = newTransferCommission;
    }

    public void setDepositAccountMinDurationInDays(int newDepositAccountMinDurationInDays) {
        if (checkDuration(newDepositAccountMinDurationInDays) == false)
            throw new IllegalArgumentException("Invalid deposit account minimal duration!");
        mDepositAccountMinDurationInDays = newDepositAccountMinDurationInDays;
    }

    public void setCreditLimit(int newCreditLimit) {
        if (checkCreditLimit(newCreditLimit) == false)
            throw new IllegalArgumentException("Invalid credit limit!");
        mCreditLimit = newCreditLimit;
    }

    public void setDubiousSum(int dubiousSum) {
        if (checkDubiousSum(dubiousSum) == false)
            throw new IllegalArgumentException("Invalid dubious sum!");
        mDubiousSum = dubiousSum;
    }

    public double getDebitPercent() {
        return mDebitPercent;
    }

    public double getCreditCommissionPercent() {
        return mCreditCommissionPercent;
    }

    public double getTransferCommission() {
        return mTransferCommission;
    }

    public int getDepositAccountMinDurationInDays() {
        return mDepositAccountMinDurationInDays;
    }

    public int getCreditLimit() {
        return mCreditLimit;
    }

    public int getDubiousSum() {
        return mDubiousSum;
    }

    private boolean checkPercent(double percent) {
        return percent > 0;
    }

    private boolean checkCreditLimit(int creditLimit) {
        return creditLimit > 0;
    }

    private boolean checkDubiousSum(int dubiousSum) {
        return dubiousSum > 0;
    }

    private boolean checkDuration(int durationInDays) {
        return durationInDays > 0;
    }
}
