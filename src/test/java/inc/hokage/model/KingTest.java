package inc.hokage.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KingTest {

    @Test
    public void givenAKingReceivesAnAppropriateMessageItsKingdomShouldPledgeAllegiance(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        oneKing.sendAllegianceInvitation("Because we need Emblem", anotherKing);

        assertTrue(oneKing.getAllies().
                contains(anotherKing.getKingdom()));
        assertTrue(anotherKing.getAllies().
                contains(oneKing.getKingdom()));
    }

    @Test
    public void givenAKingReceivesAnInappropriateMessageItsKingdomShouldNotPledgeAllegiance(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        oneKing.sendAllegianceInvitation("We don't honour your kingdom", anotherKing);

        assertFalse(oneKing.getAllies().
                contains(anotherKing.getKingdom()));
        assertFalse(anotherKing.getAllies().
                contains(oneKing.getKingdom()));
    }

    @Test
    public void kingShouldSendMessageForAllegianceToAKingdom(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King aKing = new King("One King", aKingdom);
        King bKing = new King("Another King", bKingdom);

        aKing.sendAllegianceInvitation("Test Message", bKingdom);
    }

    @Test
    public void whenAKingSendsHonourableMessageToAKingdomTheKingdomBecomesItsAlly(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King aKing = new King("One King", aKingdom);
        King bKing = new King("Another King", bKingdom);

        aKing.sendAllegianceInvitation("B Emblem", bKingdom);

        assertTrue(aKing.getAllies().contains(bKingdom));
        assertTrue(bKingdom.getKing().getAllies().contains(aKingdom));
    }

    @Test
    public void whenAKingSendsADishonourableMessageToAKingdomTheKingdomDoesNotBecomesItsAlly(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King aKing = new King("One King", aKingdom);
        King bKing = new King("Another King", bKingdom);

        aKing.sendAllegianceInvitation("No respect", bKingdom);

        assertFalse(aKing.getAllies().contains(bKingdom));
        assertFalse(bKingdom.getKing().getAllies().contains(aKingdom));
    }
}
