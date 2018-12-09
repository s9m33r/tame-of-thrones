package inc.hokage;

import com.google.common.io.Resources;
import inc.hokage.model.King;
import inc.hokage.model.Kingdom;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class UniverseTest {
    private static final String KINGDOMS_LIST_FILE_NAME = "kingdoms.txt";
    private static final String TEST_BASE_PATH = "/tmp/tame-of-thrones/";

    private Universe universe;


    @BeforeClass
    public static void globalSetup() throws Exception {
        File sourceFile = new File(Resources.getResource(KINGDOMS_LIST_FILE_NAME).toURI());
        File destinationFile = new File(TEST_BASE_PATH + KINGDOMS_LIST_FILE_NAME);

        FileUtils.copyFile(sourceFile, destinationFile);
    }

    @Test
    public void shouldSetupUniverseBasedOnAFileConfiguration() throws IOException {
        Universe universe = new Universe(TEST_BASE_PATH + KINGDOMS_LIST_FILE_NAME);

        assertAllTheKingdomsArePresent(universe);
    }

    private void assertAllTheKingdomsArePresent(Universe universe) {
        Assert.assertTrue(universe.getKingdomByName("Space").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Land").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Water").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Ice").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Fire").isPresent());
    }

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
    public void whenAKingHasSupportOfAtLeastHalfOfTheKingdomsHeShouldBeTheRuler(){
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
}
