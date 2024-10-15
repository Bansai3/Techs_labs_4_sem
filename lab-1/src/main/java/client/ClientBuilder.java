package client;

/**
 * Client builder class.
 */
public abstract class ClientBuilder extends ClientValidator {
    public abstract ClientBuilder buildName(String name);

    public abstract ClientBuilder buildSurname(String surname);

    public abstract ClientBuilder buildAddress(String address);

    public abstract ClientBuilder buildPassportNumber(String passportNumber);

    public abstract ClientBuilder buildId(int id);

    public abstract ClientBuilder buildCommonNotifications(boolean getCommonNotifications);

    public abstract Client buildClient();
}