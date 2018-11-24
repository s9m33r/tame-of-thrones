package inc.hokage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TameOfThronesTest {
    @Test
    public void shouldAnswerTheQuestionWhoIsTheRuler() {
        TameOfThrones tameOfThrones = new TameOfThrones();

        String ruler = tameOfThrones.whoIsTheRuler();

        assertEquals("None", ruler);
    }
}
