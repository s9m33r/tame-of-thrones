package inc.hokage.commands;

import inc.hokage.model.Universe;

@CommandProvider(key = "invalid-handler")
public class InvalidOptionPrinter implements Command {

    @Override
    public void run(Universe universe) {
        System.out.println("Select a valid option.");
    }
}
