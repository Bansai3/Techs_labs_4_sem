package bank;

import java.util.*;

import account.CreditAccount;
import account.DebitAccount;
import account.DepositAccount;
import client.Client;
import account.Account;
import client.ClientBuilder;
import client.ConcreteClientBuilder;
import notification.BankApplicationNotification;
import org.apache.commons.lang3.StringUtils;
import transaction.Transaction;

/**
 * Bank class.
 * Provides with all operations with accounts and clients.
 */
public class Bank {
    public static final int INITIAL_CLIENT_IDENTIFIER = 1;
    public static final int INITIAL_ACCOUNT_ID_IDENTIFIER = 1;
    private HashMap<Client, ArrayList<Account>> mClients;
    private ArrayList<Account> mDubiousAccounts;
    private HashMap<Integer, Double> mDepositPercents;
    private ArrayList<Client> mDebitNotificationClients;
    private ArrayList<Client> mDepositNotificationClients;
    private ArrayList<Client> mCreditNotificationClients;
    private BankCharacteristicParameters mBankCharacteristicParameters;
    private int mClientIdIdentifier;
    private int mAccountIdIdentifier;
    private String mTitle;

    /**
     * Constructs the object of Bank class.
     *
     * @param depositPercents                 collection of pairs (sum, sum percent).
     * @param debitPercent                    percent for debit accounts.
     * @param creditCommissionPercent         percent for credit commission.
     * @param creditLimit                     credit limit sum.
     * @param transferCommission              commission for transfer money from bank to another.
     * @param dubiousSum                      sum that limits transfer and take off operations if client does not have address or passport number.
     * @param depositAccountMinDurationInDays minimal duration of deposit accounts.
     * @param title                           title of bank.
     */
    public Bank(
            HashMap<Integer, Double> depositPercents,
            double debitPercent,
            double creditCommissionPercent,
            int creditLimit,
            double transferCommission,
            int dubiousSum,
            int depositAccountMinDurationInDays,
            String title) {
        validate(
                depositPercents,
                debitPercent,
                creditCommissionPercent,
                creditLimit,
                transferCommission,
                dubiousSum,
                depositAccountMinDurationInDays,
                title);
        mBankCharacteristicParameters = new BankCharacteristicParameters(
                debitPercent,
                creditCommissionPercent,
                transferCommission,
                depositAccountMinDurationInDays,
                creditLimit,
                dubiousSum);
        mDepositPercents = new HashMap(depositPercents);
        mClients = new HashMap();
        mDubiousAccounts = new ArrayList();
        mDebitNotificationClients = new ArrayList();
        mDepositNotificationClients = new ArrayList();
        mCreditNotificationClients = new ArrayList();
        mClientIdIdentifier = INITIAL_CLIENT_IDENTIFIER;
        mAccountIdIdentifier = INITIAL_ACCOUNT_ID_IDENTIFIER;
        mTitle = title;
    }

    public Client addClient(String name, String surname, String address, String passportNumber, boolean getCommonNotifications) {
        ClientBuilder clientBuilder = new ConcreteClientBuilder();
        Client newClient = clientBuilder
                .buildName(name)
                .buildSurname(surname)
                .buildAddress(address)
                .buildId(mClientIdIdentifier)
                .buildPassportNumber(passportNumber)
                .buildCommonNotifications(getCommonNotifications)
                .buildClient();
        mClients.put(newClient, new ArrayList());
        mClientIdIdentifier++;
        return newClient;
    }

    public void addClient(Client newClient) {
        if (checkClient(newClient) == true)
            throw new IllegalArgumentException("Invalid client!");
        mClients.put(newClient, new ArrayList());
    }

    public DebitAccount openDebitAccount(Client client, double sum, boolean getNotification) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        var debitAccount = new DebitAccount(mBankCharacteristicParameters.getDebitPercent(), mAccountIdIdentifier, sum);
        mClients.get(client).add(debitAccount);
        if (client.getAddress() == null || client.getPassportNumber() == null)
            mDubiousAccounts.add(debitAccount);
        if (getNotification) {
            if (mDebitNotificationClients.contains(client) == false)
                mDebitNotificationClients.add(client);
        }
        return debitAccount;
    }

    public DepositAccount openDepositAccount(Client client, int durationInDays, double sum, boolean getNotification) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkDuration(durationInDays) == false)
            throw new IllegalArgumentException("Invalid duration!");
        if (durationInDays < mBankCharacteristicParameters.getDepositAccountMinDurationInDays())
            throw new IllegalArgumentException("Can not open deposit account with duration less than minimal one!");
        double depositAccountPercent = definePercentForDepositAccount(sum);
        var newDepositAccount = new DepositAccount(depositAccountPercent, mAccountIdIdentifier++, durationInDays, sum);
        mClients.get(client).add(newDepositAccount);
        if (client.getAddress() == null || client.getPassportNumber() == null)
            mDubiousAccounts.add(newDepositAccount);
        if (getNotification)
            mDepositNotificationClients.add(client);
        return newDepositAccount;
    }

    public CreditAccount openCreditAccount(Client client, double sum, boolean getNotification) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (sum > mBankCharacteristicParameters.getCreditLimit())
            throw new ArithmeticException("Credit account sum is larger than credit limit!");
        var newCreditAccount = new CreditAccount(sum, mBankCharacteristicParameters.getCreditCommissionPercent(), mAccountIdIdentifier++);
        mClients.get(client).add(newCreditAccount);
        if (client.getAddress() == null || client.getPassportNumber() == null)
            mDubiousAccounts.add(newCreditAccount);
        if (getNotification)
            mCreditNotificationClients.add(client);
        return newCreditAccount;
    }

    public void takeOffMoneyFromAccount(Account account, double sum) {
        if (checkAccount(account) == false)
            throw new IllegalArgumentException("Invalid account!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (mDubiousAccounts.contains(account) && sum > mBankCharacteristicParameters.getDubiousSum())
            throw new IllegalStateException("Can not take off more money than dubious sum from dubious account!");
        if (checkAccountExisting(account) == false)
            throw new IllegalStateException("Account does not exist!");
        account.takeOff(sum);
    }

    public void topUpMoneyOnAccount(Account account, double sum) {
        if (checkAccount(account) == false)
            throw new IllegalArgumentException("Invalid account!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (checkAccountExisting(account) == false)
            throw new IllegalArgumentException("Account does not exist!");
        account.topUp(sum);
    }

    public Transaction transferMoneyToAnotherAccount(Bank bank, Account accountInThisBank, Account accountInAnotherBank, double sum) {
        if (checkBank(bank) == false)
            throw new IllegalArgumentException("Invalid bank!");
        if (checkAccount(accountInThisBank) == false)
            throw new IllegalArgumentException("Invalid accountInThisBank!");
        if (checkAccount(accountInAnotherBank) == false)
            throw new IllegalArgumentException("Invalid accountInAnotherBank!");
        if (checkSum(sum) == false)
            throw new IllegalArgumentException("Invalid sum!");
        if (bank.checkAccountExisting(accountInAnotherBank) == false)
            throw new IllegalStateException("Can not transfer money to account that does not exist in bank!");
        if (checkAccountExisting(accountInThisBank) == false)
            throw new IllegalStateException("Can not transfer money from account that does not exist!");
        if (mDubiousAccounts.contains(accountInThisBank) && sum > mBankCharacteristicParameters.getDubiousSum())
            throw new IllegalStateException("Can not transfer more money than dubious sum from dubious account!");
        double commission;
        if (bank != this)
            commission = sum * mBankCharacteristicParameters.getTransferCommission() / 100;
        else
            commission = 0;
        return accountInThisBank.transfer(sum, accountInAnotherBank, commission);
    }

    public Account findAccountById(int id) {
        for (Map.Entry<Client, ArrayList<Account>> client : mClients.entrySet()) {
            var accounts = client.getValue();
            for (Account account : accounts) {
                if (account.getId() == id)
                    return account;
            }
        }

        return null;
    }

    public void cancelTransaction(Account accountInThisBank, Transaction transaction) {
        if (checkAccount(accountInThisBank) == false)
            throw new IllegalArgumentException("Invalid account!");
        if (checkAccountExisting(accountInThisBank) == false)
            throw new IllegalStateException("Can not transfer money from account that does not exist!");
        if (checkTransaction(transaction) == false)
            throw new IllegalArgumentException("Invalid transaction!");
        accountInThisBank.topUp(transaction.getSum() + transaction.getCommission());
        transaction.getAccount().takeOff(transaction.getSum());
    }

    public int getCreditLimit() {
        return mBankCharacteristicParameters.getCreditLimit();
    }

    public void changeClientName(Client client, String newName) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        client.setName(newName);
    }

    public void changeClientSurname(Client client, String newSurname) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        client.setName(newSurname);
    }

    public void changeClientAddress(Client client, String address) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        client.setAddress(address);
        if (address != null && client.getPassportNumber() != null) {
            makeClientNormal(client);
        }
    }

    public void changeClientPassportNumber(Client client, String passportNumber) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        client.setPassportNumber(passportNumber);
        if (passportNumber != null && client.getAddress() != null) {
            makeClientNormal(client);
        }
    }

    public void changeDebitPercent(double newDebitPercent) {
        if (checkPercent(newDebitPercent) == false)
            throw new IllegalArgumentException("Invalid debit percent!");
        double oldDebitPercent = mBankCharacteristicParameters.getDebitPercent();
        mBankCharacteristicParameters.setDebitPercent(newDebitPercent);
        for (Client client : mDebitNotificationClients) {
            client.notify(new BankApplicationNotification(
                    "Debit percent changed from " + oldDebitPercent + "% " + "to " + newDebitPercent + "%"));
        }
    }

    public void changeDepositPercents(HashMap<Integer, Double> depositPercents) {
        if (checkDepositPercents(depositPercents) == false)
            throw new IllegalStateException("Invalid deposit percents!");
        mDepositPercents = new HashMap<Integer, Double>(depositPercents);
        for (Client client : mDepositNotificationClients) {
            client.notify(new BankApplicationNotification("Deposit percents changed!"));
        }
    }

    public void changeCreditCommissionPercent(double creditCommissionPercent) {
        if (checkPercent(creditCommissionPercent) == false)
            throw new IllegalArgumentException("Invalid credit commission percent!");
        double oldCreditCommissionPercent = mBankCharacteristicParameters.getCreditCommissionPercent();
        mBankCharacteristicParameters.setCreditCommissionPercent(creditCommissionPercent);
        for (Client client : mCreditNotificationClients) {
            client.notify(new BankApplicationNotification(
                    "Credit commission percent changed from " + oldCreditCommissionPercent + "% " +
                            "to " + creditCommissionPercent + "!"));
        }
    }

    public void changeTransferCommission(double transferCommission) {
        if (checkPercent(transferCommission) == false)
            throw new IllegalArgumentException("Invalid transfer commission!");
        double oldTransferCommission = mBankCharacteristicParameters.getTransferCommission();
        mBankCharacteristicParameters.setTransferCommission(transferCommission);
        for (Client client : mClients.keySet()) {
            if (client.getCommonNotificationsStatus() == true)
                client.notify(new BankApplicationNotification(
                        "Transfer commission changed from " + oldTransferCommission + "% " +
                                "to " + transferCommission + "!"));
        }
    }

    public void ChangeDepositAccountMinDurationInDays(int depositAccountMinDurationInDays) {
        if (checkDuration(depositAccountMinDurationInDays) == false)
            throw new IllegalArgumentException("Invalid deposit account minimal duration in days!");
        int oldDepositAccountMinDurationInDays = mBankCharacteristicParameters.getDepositAccountMinDurationInDays();
        mBankCharacteristicParameters.setDepositAccountMinDurationInDays(depositAccountMinDurationInDays);
        for (Client client : mDepositNotificationClients) {
            client.notify(new BankApplicationNotification(
                    "Changed deposit account minimal duration from " + oldDepositAccountMinDurationInDays + " days " +
                            "to " + depositAccountMinDurationInDays + " days!"));
        }
    }

    public void ChangeCreditLimit(int creditLimit) {
        if (checkCreditLimit(creditLimit) == false)
            throw new IllegalArgumentException("Invalid credit limit!");
        int oldCreditLimit = mBankCharacteristicParameters.getCreditLimit();
        mBankCharacteristicParameters.setCreditLimit(creditLimit);
        for (Client client : mCreditNotificationClients) {
            client.notify(new BankApplicationNotification(
                    "Credit limit changed from " + oldCreditLimit + " rubles " +
                            "to " + creditLimit + " rubles!"));
        }
    }

    public void ChangeDubiousSum(int dubiousSum) {
        if (checkDubiousSum(dubiousSum) == false)
            throw new IllegalArgumentException("Invalid dubious sum!");
        int olDubiousSum = mBankCharacteristicParameters.getDubiousSum();
        mBankCharacteristicParameters.setDubiousSum(dubiousSum);
        for (Client client : mClients.keySet()) {
            if (client.getCommonNotificationsStatus() == true)
                client.notify(new BankApplicationNotification(
                        "Dubious sum changed from " + olDubiousSum + " rubles " +
                                "to " + dubiousSum + " rubles!"));
        }
    }

    public Client getClient(int id) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
        var clients = mClients.keySet();
        for (Client client : clients) {
            if (client.getId() == id)
                return client;
        }
        throw new NoSuchElementException("There is no client with id " + id + "!");
    }

    public Client findClient(int id) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
        var clients = mClients.keySet();
        for (Client client : clients) {
            if (client.getId() == id)
                return client;
        }
        return null;
    }

    public void updatePercentsAndCommission(boolean endOfMonth) {
        for (Map.Entry<Client, ArrayList<Account>> client : mClients.entrySet()) {
            for (Account account : client.getValue()) {
                account.accruePercent();
                if (endOfMonth)
                    account.accruePercentSum();
            }
        }
    }

    public List<Account> getClientAccounts(Client client) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        return Collections.unmodifiableList(mClients.get(client));
    }

    public int getClientIdIdentifier() {
        return mClientIdIdentifier;
    }

    public int getAccountIdIdentifier() {
        return mAccountIdIdentifier;
    }

    public String getTitle() {
        return mTitle;
    }

    private void makeClientNormal(Client client) {
        if (checkClient(client) == false)
            throw new IllegalArgumentException("Invalid client!");
        for (Account account : mClients.get(client)) {
            mDubiousAccounts.remove(account);
        }
    }

    private double definePercentForDepositAccount(double sum) {
        double maxPercent = 0;
        for (Map.Entry<Integer, Double> sumPercent : mDepositPercents.entrySet()) {
            if (sum <= sumPercent.getKey())
                return sumPercent.getValue();
            maxPercent = sumPercent.getValue();
        }

        return maxPercent;
    }

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(mClients.keySet());
    }

    private boolean checkAccount(Account account) {
        return account != null;
    }

    private boolean checkDuration(int durationInDays) {
        return durationInDays > 0;
    }

    private boolean checkSum(double sum) {
        return sum >= 0;
    }

    private boolean checkAccountExisting(Account account) {
        for (Map.Entry<Client, ArrayList<Account>> entry : mClients.entrySet()) {
            if (entry.getValue().contains(account) == true)
                return true;
        }
        return false;
    }

    private boolean checkDubiousSum(int dubiousSum) {
        return dubiousSum > 0;
    }

    private boolean checkClient(Client client) {
        return client != null && mClients.containsKey(client);
    }

    private boolean checkId(int id) {
        return id > 0;
    }

    private boolean checkBank(Bank bank) {
        return bank != null;
    }

    private boolean checkTitle(String title) {
        return !StringUtils.isEmpty(title) && StringUtils.isAlpha(title);
    }

    private boolean checkPercent(double percent) {
        return percent > 0;
    }

    private boolean checkCreditLimit(int creditLimit) {
        return creditLimit > 0;
    }

    private boolean checkTransaction(Transaction transaction) {
        return transaction != null;
    }

    private boolean checkDepositPercents(HashMap<Integer, Double> depositPercents) {
        if (depositPercents == null)
            return false;
        int currentKey = -1;
        double currentDepositPercent = -1;
        for (Map.Entry<Integer, Double> sumPercent : depositPercents.entrySet()) {
            if (sumPercent.getKey() < 0 || sumPercent.getValue() < 0)
                return false;
            if (currentKey < sumPercent.getKey() && currentDepositPercent < sumPercent.getValue()) {
                currentKey = sumPercent.getKey();
                currentDepositPercent = sumPercent.getValue();
            } else {
                return false;
            }
        }

        return true;
    }

    private void validate(
            HashMap<Integer, Double> depositPercents,
            double debitPercent,
            double creditCommissionPercent,
            int creditLimit,
            double transferCommission,
            int dubiousSum,
            int depositAccountMinDurationInDays,
            String title) {
        if (checkDepositPercents(depositPercents) == false)
            throw new IllegalArgumentException("Invalid depositPercents!");
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
        if (checkTitle(title) == false)
            throw new IllegalArgumentException("Invalid title!");
    }
}
