package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import notification.Notification;
import org.jetbrains.annotations.NotNull;

/**
 * Client class.
 */
public class Client extends ClientValidator {
    private ArrayList<Notification> mNotifications;

    @NotNull
    private String mName;
    @NotNull
    private String mSurname;
    private String mAddress;
    private String mPassportNumber;
    private boolean mGetCommonNotifications;
    private int mId;

    /**
     * Constructs the object of client class.
     *
     * @param name                   client's name.
     * @param surname                client's surname.
     * @param address                client's address.
     * @param passportNumber         client's passport number.
     * @param id                     client's identifier.
     * @param getCommonNotifications notification status.
     */
    public Client(String name, String surname, String address, String passportNumber, int id, boolean getCommonNotifications) {
        validate(name, surname, address, passportNumber, id);
        mNotifications = new ArrayList<Notification>();
        mName = name;
        mSurname = surname;
        mAddress = address;
        mPassportNumber = passportNumber;
        mId = id;
        mGetCommonNotifications = getCommonNotifications;

    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getPassportNumber() {
        return mPassportNumber;
    }

    public boolean getCommonNotificationsStatus() {
        return mGetCommonNotifications;
    }

    public int getId() {
        return mId;
    }

    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(mNotifications);
    }

    public void setName(@NotNull String newName) {
        if (checkName(newName) == false)
            throw new IllegalArgumentException("Invalid name!");
        mName = newName;
    }

    public void setSurname(@NotNull String newSurname) {
        if (checkName(newSurname) == false)
            throw new IllegalArgumentException("Invalid surname!");
        mSurname = newSurname;
    }

    public void setId(int newId) {
        if (checkId(newId) == false)
            throw new IllegalArgumentException("Invalid id!");
        mId = newId;
    }

    public void setAddress(String newAddress) {
        if (checkAddress(newAddress) == false)
            throw new IllegalArgumentException("Invalid address!");
        mAddress = newAddress;
    }

    public void setPassportNumber(String passportNumber) {
        if (checkPassportNumber(passportNumber) == false)
            throw new IllegalArgumentException("Invalid passport number!");
        mPassportNumber = passportNumber;
    }

    public void setGetCommonNotifications(boolean getCommonNotifications) {
        mGetCommonNotifications = getCommonNotifications;
    }

    public void notify(Notification notification) {
        if (checkNotification(notification) == false)
            throw new IllegalArgumentException("Invalid notification format!");
        mNotifications.add(notification);
    }
}
