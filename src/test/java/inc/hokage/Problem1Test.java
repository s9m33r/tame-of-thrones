package inc.hokage;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Problem1Test {
    private static final String KINGDOMS_LIST_FILE_NAME = "kingdoms.txt";
    private static final String TEST_BASE_PATH = "/tmp/tame-of-thrones/";

    @BeforeClass
    public static void globalSetup() throws Exception {
        File sourceFile = new File(Resources.getResource(KINGDOMS_LIST_FILE_NAME).toURI());
        File destinationFile = new File(TEST_BASE_PATH + KINGDOMS_LIST_FILE_NAME);

        FileUtils.copyFile(sourceFile, destinationFile);
    }

    @Test
    public void shouldSetupUniverseBasedOnAFileConfiguration() throws IOException {
        Universe universe = Problem1.setUpUniverse(TEST_BASE_PATH + KINGDOMS_LIST_FILE_NAME);

        assertAllTheKingdomsArePresent(universe);
    }

    private void assertAllTheKingdomsArePresent(Universe universe) {
        Assert.assertTrue(universe.getKingdomByName("Space").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Land").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Water").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Ice").isPresent());
        Assert.assertTrue(universe.getKingdomByName("Fire").isPresent());
    }
}
