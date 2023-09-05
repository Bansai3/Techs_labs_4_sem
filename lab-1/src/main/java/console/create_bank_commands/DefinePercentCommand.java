package console.create_bank_commands;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define any percent via console.
 */
public class DefinePercentCommand {
    /**
     * Allows to define any percent via console.
     * @return percent.
     */
    public double definePercent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextDouble())
                return scanner.nextDouble();
            System.out.println("Invalid data format! Try again :)");
        }
    }
}
