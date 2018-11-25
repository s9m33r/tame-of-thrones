package inc.hokage.model;

import org.junit.Assert;
import org.junit.Test;

public class KingdomTest {

    @Test
    public void shouldDetectAHonourableMessage(){
        Kingdom kingdom = new Kingdom("Test Kingdom", "Test");

        boolean isMessageHonourable = kingdom.isMessageHonourable("This is emerging in the east");

        Assert.assertTrue(isMessageHonourable);
    }

    @Test
    public void shouldDetectANonHonourableMessage(){
        Kingdom kingdom = new Kingdom("Test Kingdom", "Test");

        boolean isMessageHonourable = kingdom.isMessageHonourable("Banana");

        Assert.assertFalse(isMessageHonourable);
    }
}
