package console.add_client_commands;

import client.ClientValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define address via console.
 */
public class DefineAddressCommand {
    /**
     * Allows to define valid address via console.
     *
     * @return address.
     */
    public String defineAddress() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Insert address:");
            String address = scanner.nextLine();
            if (address == null)
                return address;
            String addressCopy = address;
            addressCopy = addressCopy.replace(',', ' ');
            String[] data = StringUtils.split(addressCopy, ' ');
            if (data.length != ClientValidator.ADDRESS_PARAMETERS_COUNT ||
                    (StringUtils.isAlpha(data[0]) && StringUtils.isAlpha(data[1])) == false ||
                    (StringUtils.isNumeric(data[2]) &&
                            StringUtils.isNumeric(data[3]) && StringUtils.isNumeric(data[4]) == false)) {
                System.out.println("invalid data format! Try again :)");
                continue;
            }

            return address;
        }
    }
}
