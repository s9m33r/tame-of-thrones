package inc.hokage.commands;

import inc.hokage.Universe;

@CommandProvider(key = "invalid-handler")
public class InvalidOptionPrinter implements Command {
    @Override
    public void run(Universe universe) {
        System.out.println("Enter a valid option");
    }
}
