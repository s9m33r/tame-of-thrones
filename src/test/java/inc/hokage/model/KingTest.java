package inc.hokage.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class KingTest {

    @Test
    public void givenAKingReceivesAnAppropriateMessageItsKingdomShouldPledgeAllegiance(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        oneKing.sendAllegianceInvitation("Because we need Emblem", anotherKing);

        assertTrue(oneKing.getKingdom().getAllies().
                contains(anotherKing.getKingdom()));
        assertTrue(anotherKing.getKingdom().getAllies().
                contains(oneKing.getKingdom()));
    }

    @Test
    public void givenAKingReceivesAnInAppropriateMessageItsKingdomShouldNotPledgeAllegiance(){
        Kingdom aKingdom = new Kingdom("A Kingdom", "A Emblem");
        Kingdom bKingdom = new Kingdom("B Kingdom", "B Emblem");

        King oneKing = new King("One King", aKingdom);
        King anotherKing = new King("Another King", bKingdom);

        oneKing.sendAllegianceInvitation("Because we need Emblem", anotherKing);

        assertTrue(oneKing.getKingdom().getAllies().
                contains(anotherKing.getKingdom()));
        assertTrue(anotherKing.getKingdom().getAllies().
                contains(oneKing.getKingdom()));
    }
}
