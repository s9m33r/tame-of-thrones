package inc.hokage.commands;

import inc.hokage.Universe;

@CommandProvider(key = "2")
public class AlliesPrinter implements Command {
    @Override
    public void run(Universe universe) {
        System.out.println(universe.whoIsTheRuler().isPresent() ?
                universe.whoAreTheAlliesOf(universe.whoIsTheRuler().get()) :
                "None");
    }
}
