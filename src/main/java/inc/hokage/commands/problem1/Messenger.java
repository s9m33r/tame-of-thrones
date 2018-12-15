package inc.hokage.commands.problem1;

import inc.hokage.commands.Command;
import inc.hokage.commands.CommandProvider;
import inc.hokage.model.Universe;
import inc.hokage.model.Kingdom;

import java.util.Optional;
import java.util.Scanner;

@CommandProvider(key = "3")
public class Messenger implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(Universe universe) {
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        System.out.print("From(Name of the Kingdom): ");
        String fromKingdomName = scanner.nextLine();
        System.out.print("To(Name of the Kingdom): ");
        String toKingdomName = scanner.nextLine();

        Optional<Kingdom> fromKingdom = universe.getKingdomByName(fromKingdomName);
        Optional<Kingdom> toKingdom = universe.getKingdomByName(toKingdomName);

        if(fromKingdom.isPresent() && toKingdom.isPresent()) {
            fromKingdom.get().getKing().sendAllegianceInvitation(message, toKingdom.get());
            System.out.println(String.format("Message sent from %s to %s kingdom!",
                    fromKingdomName, toKingdomName));
        } else
            System.out.println("There is no such kingdom");
    }
}
