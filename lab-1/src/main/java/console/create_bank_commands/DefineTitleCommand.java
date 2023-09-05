package console.create_bank_commands;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define title via console.
 */
public class DefineTitleCommand {
    /**
     * Allows to define any valid title via console.
     *
     * @return title.
     */
    public String defineTitle() {
        String title;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            title = scanner.nextLine();
            if (StringUtils.isAlpha(title))
                return title;
            System.out.println("invalid data format! Try again :)");
        }
    }
}
