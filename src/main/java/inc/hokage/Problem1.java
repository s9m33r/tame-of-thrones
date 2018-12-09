package inc.hokage;

import inc.hokage.commands.CommandFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

public class Problem1 {
    private static Universe universe = new Universe();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        setUpUniverse(args[0]);
        String userChoice;

        do{
            CommandFactory.getCommand("print-options").run(universe);
            userChoice = scanner.nextLine();
            CommandFactory.getCommand(userChoice).run(universe);
        } while(!userChoice.equals("4"));
    }

    public static Universe setUpUniverse(String kingdomsListFilePath) throws IOException {
        List<String> kingdomsEntries = FileUtils.readLines(new File(kingdomsListFilePath), Charset.defaultCharset());

        kingdomsEntries.forEach(kingdomsEntry -> {
            String[] splitedEntry = kingdomsEntry.split(",");
            if(splitedEntry.length == 3){
                universe.addKingdom(splitedEntry[0].trim(),
                        splitedEntry[1].trim(),
                        splitedEntry[2].trim());
            }
        });

        return universe;
    }

}
