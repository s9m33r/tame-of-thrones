package inc.hokage.command;

import inc.hokage.Universe;
import inc.hokage.commands.RulerPrinter;
import inc.hokage.model.King;
import inc.hokage.model.Kingdom;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RulerPrinterTest {
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
    public void shouldPrintTheRulerAsNoneWhenNoRulerInTheUniverse(){
        Universe universe = mock(Universe.class);
        when(universe.whoIsTheRuler()).thenReturn(Optional.empty());
        RulerPrinter rulerPrinter = new RulerPrinter();

        rulerPrinter.run(universe);

        Assert.assertEquals("None\n", outContent.toString());
    }

    @Test
    public void shouldPrintTheRulerOfTheUniverse(){
        Universe universe = mock(Universe.class);
        when(universe.whoIsTheRuler()).thenReturn(Optional.of(new King("Shan", new Kingdom(
                "Test Kingdom", "Test Emblem"))));
        RulerPrinter rulerPrinter = new RulerPrinter();

        rulerPrinter.run(universe);

        Assert.assertEquals("Shan\n", outContent.toString());
    }
}
