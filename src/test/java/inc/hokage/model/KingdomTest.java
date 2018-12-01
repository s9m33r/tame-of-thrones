package inc.hokage.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingdomTest {

    @Test
    public void shouldIdentifyAHonourableMessage(){
        Kingdom kingdom = new Kingdom("Test Kingdom", "Test");

        boolean isMessageHonourable = kingdom.isMessageHonourable("This is emerging in the east");

        Assert.assertTrue(isMessageHonourable);
    }

    @Test
    public void shouldIdentifyADishonourableMessage(){
        Kingdom kingdom = new Kingdom("Test Kingdom", "Test");

        boolean isMessageHonourable = kingdom.isMessageHonourable("Banana");

        assertFalse(isMessageHonourable);
    }

    @Test
    public void givenAKingdomReceivesAHonourableMessageFromAKingItShouldPledgeAllegianceToTheKing(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        bKingdom.willPledgeAllegiance("B Emblem", oneKing);

        assertTrue(anotherKing.getAllies().
                contains(oneKing.getKingdom()));
    }

    @Test
    public void givenAKingdomReceivesADishonourableMessageFromAKingItShouldNotPledgeAllegianceToTheKing(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        bKingdom.willPledgeAllegiance("No respect", oneKing);

        assertFalse(anotherKing.getAllies().
                contains(oneKing.getKingdom()));
    }
}
