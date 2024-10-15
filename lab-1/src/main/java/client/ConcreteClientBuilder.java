package client;

/**
 * Concrete client builder class.
 * Builds client step by step.
 */
public class ConcreteClientBuilder extends ClientBuilder {
    private Client mClient;
    private String mName;
    private String mSurname;
    private String mAddress;
    private String mPassportNumber;
    private int mId;
    private boolean mGetCommonNotifications;

    @Override
    public ConcreteClientBuilder buildName(String name) {
        if (checkName(name) == false)
            throw new IllegalArgumentException("Invalid name!");
        mName = name;
        return this;
    }

    @Override
    public ConcreteClientBuilder buildSurname(String surname) {
        if (checkName(surname) == false)
            throw new IllegalArgumentException("Invalid surname!");
        mSurname = surname;
        return this;
    }

    @Override
    public ConcreteClientBuilder buildAddress(String address) {
        if (checkAddress(address) == false)
            throw new IllegalArgumentException("Invalid address!");
        mAddress = address;
        return this;
    }

    @Override
    public ConcreteClientBuilder buildPassportNumber(String passportNumber) {
        if (checkPassportNumber(passportNumber) == false)
            throw new IllegalArgumentException("Invalid passport number!");
        mPassportNumber = passportNumber;
        return this;
    }

    @Override
    public ConcreteClientBuilder buildId(int id) {
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
        mId = id;
        return this;
    }

    @Override
    public ConcreteClientBuilder buildCommonNotifications(boolean getCommonNotifications) {
        mGetCommonNotifications = getCommonNotifications;
        return this;
    }

    /**
     * Builds client.
     *
     * @return object of class Client.
     */
    @Override
    public Client buildClient() {
        validate(mName, mSurname, mAddress, mPassportNumber, mId);
        return mClient = new Client(mName, mSurname, mAddress, mPassportNumber, mId, mGetCommonNotifications);
    }
}
