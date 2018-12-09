package inc.hokage.command;

import inc.hokage.Universe;
import inc.hokage.commands.ExitMessagePrinter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExitMessagePrinterTest {
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
    public void shouldPrintTheExitMessageAsExpected(){
        Universe universe = Mockito.mock(Universe.class);
        ExitMessagePrinter exitMessagePrinter = new ExitMessagePrinter();

        exitMessagePrinter.run(universe);

        Assert.assertEquals("Exiting...\n", outContent.toString());
    }
}
