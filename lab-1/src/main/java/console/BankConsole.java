package console;

import console.auxiliary_commands.CommandHandler;
import console.auxiliary_commands.HelpCommandHandler;

import java.util.Scanner;

/**
 * BankConsole class.
 * Provides with console interface, which controls bank system work.
 */
public class BankConsole {
    private CommandHandler mCommandHandler;

    public BankConsole() {
        mCommandHandler = new HelpCommandHandler();
    }

    /**
     * Launches console interface.
     */
    public void launch() {
        System.out.println("Bank Interface :)");
        System.out.println("Use command help to look at ALL available commands");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String command = scanner.nextLine();
            mCommandHandler.executeCommand(command);
        }
    }
}
