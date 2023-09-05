package console.add_client_commands;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define passport number via console.
 */
public class DefinePassportNumberCommand {

    /**
     * Allows to define valid passport number via console.
     *
     * @return passport number.
     */
    public String definePassportNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Insert passport number:");
            String passportNumber = scanner.nextLine();
            if (passportNumber == null || StringUtils.isNumeric(passportNumber))
                return passportNumber;
            System.out.println("invalid data format! Try again :)");
        }
    }
}
