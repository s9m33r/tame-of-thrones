package inc.hokage;

import inc.hokage.commands.CommandFactory;
import inc.hokage.model.Universe;

import java.io.IOException;
import java.util.Scanner;

public class Problem1 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Universe universe = new Universe(args[0]);

        String userChoice;

        do{
            CommandFactory.getCommand("print-options").run(universe);
            userChoice = scanner.nextLine();
            CommandFactory.getCommand(userChoice).run(universe);
        } while(!userChoice.equals("4"));
    }
}
