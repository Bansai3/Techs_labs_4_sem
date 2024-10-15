package transaction;

import account.Account;

public class Transaction {
    private Account mAccount;
    private double mSum;
    private double mCommission;

    public Transaction(Account account, double sum, double commission) {
        if (checkAccount(account) == false)
            throw new IllegalArgumentException("Invalid account!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if(checkCommission(commission) == false)
            throw new IllegalArgumentException("invalid commission!");
        mAccount = account;
        mSum = sum;
        mCommission = commission;
    }

    public Account getAccount() {
        return mAccount;
    }

    public double getSum(){
        return mSum;
    }

    public double getCommission() {
        return mCommission;
    }
    private boolean checkAccount(Account account) {
        return account != null;
    }

    private boolean checkSum(double sum) {
        return sum >= 0;
    }

    private boolean checkCommission(double commission) {
        return commission >= 0;
    }
}
