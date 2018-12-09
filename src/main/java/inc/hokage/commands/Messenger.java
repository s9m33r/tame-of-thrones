package inc.hokage.commands;

import inc.hokage.Universe;
import inc.hokage.model.King;
import inc.hokage.model.Kingdom;

import java.util.Optional;
import java.util.Scanner;

@CommandProvider(key = "3")
public class Messenger implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(Universe universe) {
        King kingShan = universe.getKingdomByName("Space").get().getKing();

        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        System.out.print("To(Name of the Kingdom): ");
        String kingdomName = scanner.nextLine();

        Optional<Kingdom> kingdom = universe.getKingdomByName(kingdomName);

        if(kingdom.isPresent()) {
            System.out.println(String.format("Message sent to %s kingdom!", kingdomName));
            kingShan.sendAllegianceInvitation(message, kingdom.get());
        } else
            System.out.println("There is no such kingdom");
    }
}
