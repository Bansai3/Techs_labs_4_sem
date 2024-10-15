package bank;

import java.util.*;

/**
 * Central bank class.
 * Allows to create only one instance of class.
 */
public class CentralBank {
    private static CentralBank mCentralBank;
    private ArrayList<Bank> mBanks;

    private CentralBank() {
        mBanks = new ArrayList<>();
    }

    public List<Bank> getBanks() {
        return Collections.unmodifiableList(mBanks);
    }

    /**
     * Provides the only one instance of central bank.
     *
     * @return the reference on only one central bank instance.
     */
    public static CentralBank getCentralBankInstance() {
        if (mCentralBank == null)
            mCentralBank = new CentralBank();
        return mCentralBank;
    }

    public Bank registerBank(
            HashMap<Integer, Double> depositPercents,
            double debitPercent,
            double creditCommissionPercent,
            int creditLimit,
            double transferCommission,
            int dubiousSum,
            int depositAccountMinDurationInDays,
            String title) {
        if (checkTitle(title) == false)
            throw new IllegalArgumentException("Invalid title!");
        if (checkSimilarTitles(title) == false)
            throw new IllegalArgumentException("Bank with title " + title + " already exists!");
        var newBank = new Bank(
                depositPercents,
                debitPercent,
                creditCommissionPercent,
                creditLimit,
                transferCommission,
                dubiousSum,
                depositAccountMinDurationInDays,
                title);
        mBanks.add(newBank);
        return newBank;
    }

    public void accrueCommissionAndPercents(int durationDays) {
        var currentDate = new GregorianCalendar(2022, Calendar.JANUARY, 01);
        for (int i = 0; i < durationDays; i++) {
            boolean endOfMonth = currentDate.get(Calendar.DAY_OF_MONTH) == currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (Bank bank : mBanks) {
                bank.updatePercentsAndCommission(endOfMonth);
            }

            currentDate.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public Bank getBankById(int id) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
        return mBanks.get(id);
    }

    public Bank findBankByTitle(String title) {
        if (checkTitle(title) == false)
            throw new IllegalArgumentException("Invalid title!");
        for (Bank bank : mBanks) {
            if (bank.getTitle() == title)
                return bank;
        }
        return null;
    }

    private boolean checkSimilarTitles(String title) {
        for (Bank bank : mBanks) {
            if (bank.getTitle() == title)
                return false;
        }
        return true;
    }

    private boolean checkTitle(String title) {
        return title != null;
    }

    private boolean checkId(int id) {
        return id >= 0 && id < mBanks.size();
    }
}
