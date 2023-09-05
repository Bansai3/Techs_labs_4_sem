package bank;

import account.DebitAccount;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import client.Client;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that provides with methods that allow to test CentralBank class functions.
 */
class CentralBankTest {

    private HashMap<Integer, Double> mDepositPercents = new HashMap<Integer, Double>() {{
        put(50000, 3.0);
        put(100000, 4.0);
        put(150000, 5.0);
    }};

    private double mDebitPercent = 3.65;
    private double mCreditCommissionPercent = 1;
    private int mCreditLimit = 100000;
    private double mTransferCommission = 1;
    private int mDubiousSum = 50000;
    private int mDepositAccountMinDurationInDays = 10;
    private CentralBank mCentralBank = CentralBank.getCentralBankInstance();

    @Test
    void registerBank() {
        Bank tinkoffBank = mCentralBank.registerBank(
                mDepositPercents,
                mDebitPercent,
                mCreditCommissionPercent,
                mCreditLimit,
                mTransferCommission,
                mDubiousSum,
                mDepositAccountMinDurationInDays,
                "TinkoffBank");
        assertEquals(tinkoffBank, mCentralBank.findBankByTitle("TinkoffBank"));
    }

    @Test
    void accrueCommissionAndPercents() {
        Bank spbBank = mCentralBank.registerBank(
                mDepositPercents,
                mDebitPercent,
                mCreditCommissionPercent,
                mCreditLimit,
                mTransferCommission,
                mDubiousSum,
                mDepositAccountMinDurationInDays,
                "spbBank");
        Client client = spbBank.addClient(
                "Ben",
                "Simmons",
                "SPB, Sovietskaya, 5, 123, 3 ",
                "123",
                true);
        DebitAccount account = spbBank.openDebitAccount(client, 100000, true);
        mCentralBank.accrueCommissionAndPercents(32);
        double sum = 100310;
        assertEquals(sum, account.getSum());
    }
}