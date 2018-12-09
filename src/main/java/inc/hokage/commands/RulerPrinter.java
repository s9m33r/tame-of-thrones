package inc.hokage.commands;

import inc.hokage.Universe;
import inc.hokage.model.King;

import java.util.Optional;

@CommandProvider(key = "1")
public class RulerPrinter implements Command {
    @Override
    public void run(Universe universe) {
        Optional<King> ruler = universe.whoIsTheRuler();
        System.out.println(ruler.isPresent() ? ruler.get().getName() : "None");
    }
}
