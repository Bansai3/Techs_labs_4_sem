package notification;

import org.apache.commons.lang3.StringUtils;

/**
 * Bank application notification class.
 */
public class BankApplicationNotification implements Notification {
    private String mMessage;

    /**
     * Constructs the object of BankApplicationNotification class.
     *
     * @param message notification message.
     */
    public BankApplicationNotification(String message) {
        if (checkMessage(message) == false)
            throw new IllegalArgumentException("Invalid message!");
        mMessage = message;
    }

    @Override
    public void inform() {
        System.out.println("Bank application message: " + mMessage);
    }

    private boolean checkMessage(String message) {
        return !StringUtils.isEmpty(message);
    }
}
