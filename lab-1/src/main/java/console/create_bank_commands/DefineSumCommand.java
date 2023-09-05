package console.create_bank_commands;

import java.util.Scanner;

/**
 * Class that provides with opportunity to define any sum via console.
 */
public class DefineSumCommand {
    /**
     * Allows to define any sum.
     * @return sum.
     */
    public int defineSum(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt())
                return scanner.nextInt();
            System.out.println("Invalid data format! Try again :)");
        }
    }
}
