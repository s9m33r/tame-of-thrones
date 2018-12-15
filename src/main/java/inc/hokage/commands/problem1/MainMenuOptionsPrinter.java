package inc.hokage.commands.problem1;

import inc.hokage.commands.Command;
import inc.hokage.commands.CommandProvider;
import inc.hokage.model.Universe;

@CommandProvider(key = "print-options")
public class MainMenuOptionsPrinter implements Command {

    @Override
    public void run(Universe universe) {
        System.out.println("1. Who is the ruler of Southeros?");
        System.out.println("2. Allies of the ruler?");
        System.out.println("3. Send a message.");
        System.out.println("4. Quit.");
    }
}
