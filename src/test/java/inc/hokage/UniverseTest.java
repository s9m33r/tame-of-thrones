package inc.hokage;

import inc.hokage.model.King;
import inc.hokage.model.Kingdom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class UniverseTest {
    private Universe universe;

    @Before
    public void setupTest(){
        universe = new Universe();
    }

    @Test
    public void shouldAnswerTheQuestionWhoIsTheRuler() {
        Optional<King> ruler = universe.whoIsTheRuler();

        assertFalse(ruler.isPresent());
    }

    @Test
    public void shouldAnswerTheQuestionWhoAreTheAllies(){
        King kingShan = new King("Shan", new Kingdom("A Kingdom", "A Emblem"));
        Set<Kingdom> allies = kingShan.getAllies();

        Assert.assertTrue(allies.isEmpty());
    }

    @Test
    public void whenAKingHasSupportOfAtleastHalfOfTheKingdomsHeShouldBeTheRuler(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");
        Kingdom cKingdom = new Kingdom("C Kingdom", "C Emblem");

        King aKing = new King("A King", aKingdom);
        King bKing = new King("B King", bKingdom);
        King cKing = new King("C King", cKingdom);

        universe.addKingdom(aKingdom);
        universe.addKingdom(bKingdom);
        universe.addKingdom(cKingdom);

        aKing.sendAllegianceInvitation("B Emblem", bKing);
        aKing.sendAllegianceInvitation("C Emblem", cKing);

        Optional<King> king = universe.whoIsTheRuler();

        assertTrue(king.isPresent());
        assertEquals(aKingdom.getKing(), king.get());

        Set<Kingdom> alliesOfTheKing = king.get().getAllies();
        assertEquals(2, alliesOfTheKing.size());
        assertTrue(alliesOfTheKing.contains(bKingdom));
        assertTrue(alliesOfTheKing.contains(cKingdom));
    }

    @Test
    public void shouldAddAKingdomToTheUniverse(){
        Kingdom kingdom = universe.addKingdom("Test Name", "Test Emblem", "Test King Name");

        Assert.assertEquals(kingdom.getName(), "Test Name");
    }
}
