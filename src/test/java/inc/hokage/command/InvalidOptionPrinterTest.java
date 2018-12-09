package inc.hokage.command;

import inc.hokage.Universe;
import inc.hokage.commands.InvalidOptionPrinter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InvalidOptionPrinterTest {
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
    public void shouldPrintInvalidOptionMessageAsExpected(){
        Universe universe = Mockito.mock(Universe.class);
        InvalidOptionPrinter invalidOptionPrinter = new InvalidOptionPrinter();

        invalidOptionPrinter.run(universe);

        Assert.assertEquals("Select a valid option.\n", outContent.toString());
    }
}
