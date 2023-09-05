package console.add_client_commands;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define notification status via console.
 */
public class DefineNotificationsCommand {
    /**
     * Allows to define notification status via console.
     *
     * @return notification status.
     */

    public boolean defineNotifications() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Insert true if you want to get notifications else false");
            if (scanner.hasNextBoolean())
                return scanner.nextBoolean();
            System.out.println("invalid data format! Try again :)");
        }
    }
}
