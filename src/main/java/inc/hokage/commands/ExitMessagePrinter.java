package inc.hokage.commands;

import inc.hokage.model.Universe;

@CommandProvider(key = "4")
public class ExitMessagePrinter implements Command {

    @Override
    public void run(Universe universe) {
        System.out.println("Exiting...");
    }
}
