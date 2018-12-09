package inc.hokage.command;

import inc.hokage.Universe;
import inc.hokage.commands.MainMenuOptionsPrinter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainMenuOptionsPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldPrintRequiredOptions(){
        Universe universe = new Universe();
        MainMenuOptionsPrinter mainMenuOptionsPrinter = new MainMenuOptionsPrinter();

        mainMenuOptionsPrinter.run(universe);

        String expectedOutput = "1. Who is the ruler of Southeros?\n" +
                                "2. Allies of the ruler?\n" +
                                "3. Send a message.\n" +
                                "4. Quit.\n";

        Assert.assertEquals(expectedOutput, outContent.toString());
    }
}
