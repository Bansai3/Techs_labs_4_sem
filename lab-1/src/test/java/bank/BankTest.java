package bank;

import account.CreditAccount;
import account.DebitAccount;
import account.DepositAccount;
import client.Client;
import org.junit.jupiter.api.Test;
import transaction.Transaction;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class that provides with methods that allow to test Bank class functions.
 */
class BankTest {

    private HashMap<Integer, Double> mDepositPercents = new HashMap<>() {{
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
    private Bank mSberBank;
    private Bank mAlphaBank;
    private Bank mSpbBank;
    private Client mSberBankClient;
    private Client mAlphaBankClient;
    private Client mSpbBankClient;

    /**
     * Constructs the object of BankTest class.
     * Creates 3 objects of Bank class: mSberBank, mAlphaBank, mSpbBank.
     * Creates 3 objects of Client class: mSberBankClient, mAlphaBankClient, mSpbBankClient.
     */
    public BankTest(){
         mSberBank = mCentralBank.findBankByTitle("SberBank");
         if (mSberBank == null) {
             mSberBank = mCentralBank.registerBank(
                     mDepositPercents,
                     mDebitPercent,
                     mCreditCommissionPercent,
                     mCreditLimit,
                     mTransferCommission,
                     mDubiousSum,
                     mDepositAccountMinDurationInDays,
                     "SberBank");
         }
         mAlphaBank = mCentralBank.findBankByTitle("AlphaBank");
         if (mAlphaBank == null) {
             mAlphaBank = mCentralBank.registerBank(
                     mDepositPercents,
                     mDebitPercent,
                     mCreditCommissionPercent,
                     mCreditLimit,
                     mTransferCommission,
                     mDubiousSum,
                     mDepositAccountMinDurationInDays,
                     "AlphaBank");
         }
         mSpbBank = mCentralBank.findBankByTitle("SpbBank");
         if (mSpbBank == null) {
             mSpbBank = mCentralBank.registerBank(
                     mDepositPercents,
                     mDebitPercent,
                     mCreditCommissionPercent,
                     mCreditLimit,
                     mTransferCommission,
                     mDubiousSum,
                     mDepositAccountMinDurationInDays,
                     "SpbBank");
         }
        mSberBankClient = mSberBank.findClient(1);
         if (mSberBankClient == null) {
             mSberBankClient = mSberBank.addClient(
                     "Ben",
                     "Simmons",
                     "SPB, Sovietskaya, 5, 123, 3 ",
                     "123",
                     true);
         }
        mSpbBankClient = mSpbBank.findClient(1);
         if (mSpbBankClient == null) {
             mSpbBankClient = mSpbBank.addClient(
                     "Victor",
                     "Simmons",
                     "SPB, Sovietskaya, 5, 123, 3 ",
                     "123",
                     true);
         }
        mAlphaBankClient = mAlphaBank.findClient(1);
         if (mAlphaBankClient == null) {
             mAlphaBankClient = mAlphaBank.addClient(
                     "Ray",
                     "Plumber",
                     "LosAngeles, SaintStreet, 5, 4, 3 ",
                     "123",
                     true);
         }
    }

    @Test
    void addClient() {
        assertEquals(mSberBankClient,  mSberBank.findClient(1));
    }

    @Test
    void openDebitAccount() {
        DebitAccount debitAccount = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        assertEquals(true, mSpbBank.getClientAccounts(mSpbBankClient).contains(debitAccount));
    }

    @Test
    void openDepositAccount() {
        DepositAccount depositAccount = mAlphaBank.openDepositAccount(mAlphaBankClient, 100, 100000, true);
        assertEquals(true, mAlphaBank.getClientAccounts(mAlphaBankClient).contains(depositAccount));
    }

    @Test
    void openCreditAccount() {
        CreditAccount creditAccount = mAlphaBank.openCreditAccount(mAlphaBankClient,100000, true);
        assertEquals(true, mAlphaBank.getClientAccounts(mAlphaBankClient).contains(creditAccount));
    }

    @Test
    void takeOffMoneyFromAccount() {
        DebitAccount debitAccount = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        mSpbBank.takeOffMoneyFromAccount(debitAccount, 10000);
        assertEquals(90000, debitAccount.getSum());
    }

    @Test
    void topUpMoneyOnAccount() {
        DebitAccount debitAccount = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        mSpbBank.topUpMoneyOnAccount(debitAccount, 10000);
        assertEquals(110000, debitAccount.getSum());
    }

    @Test
    void transferMoneyToAnotherAccount() {
        DebitAccount debitAccount1 = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        DebitAccount debitAccount2 = mAlphaBank.openDebitAccount(mAlphaBankClient, 100000, true);
        mSpbBank.transferMoneyToAnotherAccount(mAlphaBank, debitAccount1, debitAccount2, 10000);
        double transferSum = 10000 + 10000 * mTransferCommission / 100;
        double resSum = 100000 - transferSum;
        assertEquals(resSum, debitAccount1.getSum());
        assertEquals(110000, debitAccount2.getSum());
    }

    @Test
    void cancelTransaction() {
        DebitAccount debitAccount1 = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        DebitAccount debitAccount2 = mAlphaBank.openDebitAccount(mAlphaBankClient, 100000, true);
        Transaction transaction = mSpbBank.transferMoneyToAnotherAccount(mAlphaBank, debitAccount1, debitAccount2, 10000);
        mSpbBank.cancelTransaction(debitAccount1, transaction);
        assertEquals(100000, debitAccount1.getSum());
        assertEquals(100000, debitAccount2.getSum());
    }

    @Test
    void changeDebitPercent() {
        DebitAccount debitAccount1 = mSpbBank.openDebitAccount(mSpbBankClient, 100000, true);
        DebitAccount debitAccount2 = mAlphaBank.openDebitAccount(mAlphaBankClient, 100000, true);
        DebitAccount debitAccount3 = mSberBank.openDebitAccount(mSberBankClient, 100000, true);
        mSberBank.changeDebitPercent(4);
        mAlphaBank.changeDebitPercent(5);
        mSpbBank.changeDebitPercent(6);
        assertEquals(1, mSberBankClient.getNotifications().size());
        assertEquals(1, mAlphaBankClient.getNotifications().size());
        assertEquals(1, mSpbBankClient.getNotifications().size());
    }
}