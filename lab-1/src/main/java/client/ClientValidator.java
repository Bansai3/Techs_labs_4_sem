package client;

import org.apache.commons.lang3.StringUtils;
import notification.Notification;

/**
 * Client validator class.
 * Contains methods, which validate client's state.
 */
public class ClientValidator {
    public static final int ADDRESS_PARAMETERS_COUNT = 5;
    protected boolean checkName(String name){
        return !StringUtils.isEmpty(name) && StringUtils.isAlpha(name);
    }

    protected boolean checkAddress(String address) {
        if (address == null)
            return true;
        address = StringUtils.replace(address, ",", " ");
        String[] data = StringUtils.split(address, ' ');
        if (data.length != ADDRESS_PARAMETERS_COUNT)
            return false;
        if (StringUtils.isAlpha(data[0]) && StringUtils.isAlpha(data[1]) == false)
            return false;
        return StringUtils.isNumeric(data[2]) && StringUtils.isNumeric(data[3]) && StringUtils.isNumeric(data[4]);
    }

    protected boolean checkPassportNumber(String passportNumber) {
        return StringUtils.isEmpty(passportNumber) || StringUtils.isNumeric(passportNumber);
    }

    protected boolean checkId(int id){
        return id > 0;
    }


    protected boolean checkNotification(Notification notification){
        return notification != null;
    }
    protected void validate(String name, String surname, String address, String passportNumber, int id) {
        if (checkName(name) == false)
            throw new IllegalArgumentException("Invalid name!");
        if (checkName(surname) == false)
            throw new IllegalArgumentException("Invalid new surname!");
        if (checkAddress(address) == false)
            throw new IllegalArgumentException("Invalid address!");
        if (checkPassportNumber(passportNumber) == false)
            throw new IllegalArgumentException("Invalid passport number!");
        if (checkId(id) == false)
            throw new IllegalArgumentException("Invalid id!");
    }
}
