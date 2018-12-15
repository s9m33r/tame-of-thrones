package inc.hokage.command;

import inc.hokage.model.Universe;
import inc.hokage.commands.AlliesPrinter;
import inc.hokage.model.King;
import inc.hokage.model.Kingdom;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlliesPrinterTest {
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
    public void shouldPrintTheAlliesOfTheRulerAsNoneWhenNoRulerInTheUniverse(){
        Universe universe = mock(Universe.class);
        when(universe.whoIsTheRuler()).thenReturn(Optional.empty());

        AlliesPrinter alliesPrinter = new AlliesPrinter();

        alliesPrinter.run(universe);

        Assert.assertEquals("None\n", outContent.toString());
    }

    @Test
    public void shouldPrintTheAlliesOfTheRulerOfTheUniverse(){
        Universe universe = mock(Universe.class);
        Kingdom rulingKingdom = new Kingdom("Ruling Kingdom", "Ruling Emblem");
        Kingdom ally1 = new Kingdom("Ally Kingdom 1", "Emblem 1");
        Kingdom ally2 = new Kingdom("Ally Kingdom 2", "Emblem 2");
        Set<Kingdom> allies = new HashSet<>();
        allies.add(ally1);
        allies.add(ally2);
        King rulingKing = new King("Ruling King", rulingKingdom);
        when(universe.whoIsTheRuler()).thenReturn(Optional.of(rulingKing));
        when(universe.whoAreTheAlliesOf(rulingKing)).thenReturn(allies);
        AlliesPrinter alliesPrinter = new AlliesPrinter();

        alliesPrinter.run(universe);

        Assert.assertEquals(allies.toString() + "\n", outContent.toString());
    }
}
