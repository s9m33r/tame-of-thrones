package inc.hokage;

import inc.hokage.model.King;
import inc.hokage.model.Kingdom;

import java.util.Optional;
import java.util.Scanner;

public class Problem1 {
    private static Universe universe;
    private static Scanner scanner = new Scanner(System.in);
    private static King kingShan;

    public static void main(String[] args) {
        setUpTheUniverseOfSoutheros();

        String userChoice;
        do{
            displayOptions();
            userChoice = scanner.nextLine();

            switch (userChoice){
                case "1":
                    printRuler();
                    break;
                case "2":
                    printAlliesOfTheRuler();
                    break;
                case "3":
                    sendMessage();
                    break;
                case "4":
                    printExitMessage();
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while(!userChoice.equals("4"));
    }

    private static void setUpTheUniverseOfSoutheros() {
        universe = new Universe();

        Kingdom spaceKingdom = new Kingdom("Space", "Gorilla");
        kingShan = new King("Shan", spaceKingdom);
        spaceKingdom.makeKing(kingShan);
        universe.addKingdom(spaceKingdom);

        Kingdom landKingdom = new Kingdom("Land", "Panda");
        King kingMax = new King("Max", landKingdom);
        landKingdom.makeKing(kingMax);
        universe.addKingdom(landKingdom);

        Kingdom waterKingdom = new Kingdom("Water", "Octopus");
        King kingJade = new King("Jade", waterKingdom);
        waterKingdom.makeKing(kingJade);
        universe.addKingdom(waterKingdom);

        Kingdom iceKingdom = new Kingdom("Ice", "Mammoth");
        King kingMinn = new King("Minn", iceKingdom);
        iceKingdom.makeKing(kingMinn);
        universe.addKingdom(iceKingdom);

        Kingdom airKingdom = new Kingdom("Air", "Owl");
        King kingShaw = new King("Shaw", airKingdom);
        airKingdom.makeKing(kingShaw);
        universe.addKingdom(airKingdom);

        Kingdom fireKingdom = new Kingdom("Fire", "Dragon");
        King kingHank = new King("Hank", fireKingdom);
        fireKingdom.makeKing(kingHank);
        universe.addKingdom(fireKingdom);
    }

    private static void printRuler() {
        Optional<King> ruler = universe.whoIsTheRuler();
        System.out.println(ruler.isPresent() ? ruler.get().getName() : "None");
    }

    private static void printAlliesOfTheRuler() {
        System.out.println(universe.whoIsTheRuler().isPresent() ?
                universe.whoAreTheAlliesOf(universe.whoIsTheRuler().get()) :
                "None");
    }

    private static void sendMessage() {
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

    private static void printExitMessage() {
        System.out.println("Exiting...");
    }

    private static void displayOptions() {
        System.out.println("1. Who is the ruler of Southeros?");
        System.out.println("2. Allies of the ruler?");
        System.out.println("3. Send a message.");
        System.out.println("4. Quit.");
    }
}
