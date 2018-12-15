package inc.hokage.model;

import inc.hokage.model.Ballot.BallotInitializationException;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.mockito.Mockito;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BallotTest {
    private static final String TEST_ROOT_PATH = "/tmp/ballot-test/";
    private static final String MESSAGE_FILE_PATH = TEST_ROOT_PATH + "messages.txt";
    private static List<String> initializedMessages = new ArrayList<>();

    private Random random;
    private Ballot ballot;

    @BeforeClass
    public static void globalSetup() throws IOException, URISyntaxException {
        URL testDirectoryURLFromResources = BallotTest.class.getClassLoader().
                getResource("ballot-test");
        File testDirectoryFromResources = Paths.get(testDirectoryURLFromResources.toURI()).toFile();
        File testDirectory = Paths.get(TEST_ROOT_PATH).toFile();

        FileUtils.copyDirectory(testDirectoryFromResources, testDirectory);

        createInitializedMessageList(MESSAGE_FILE_PATH);
    }

    private static void createInitializedMessageList(String messageFilePath) throws IOException {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(messageFilePath))) {
            initializedMessages = bufferedReader.lines().map(String::trim).collect(Collectors.toList());
        }
    }

    @AfterClass
    public static void globalCleanUp() throws IOException {
        File testDirectory = Paths.get(TEST_ROOT_PATH).toFile();
        FileUtils.deleteDirectory(testDirectory);
    }

    @Before
    public void setup() {
        random = mock(Random.class);
        ballot = new Ballot(MESSAGE_FILE_PATH);
        ballot.setRandom(random);
    }

    @Test
    public void shouldDrawCorrectMessagesFromTheBallot() {
        IntStream.range(0, initializedMessages.size()).
                forEach(this::assertDrawnMessageIsAmongInitializedMessage);
    }

    private void assertDrawnMessageIsAmongInitializedMessage(int i) {
        when(random.nextInt(Mockito.anyInt())).thenReturn(i);
        String message = ballot.drawMessage();
        Assert.assertEquals(initializedMessages.get(i), message);
    }

    @Test(expected = BallotInitializationException.class)
    public void shouldThrowAnExceptionWhenTheMessageFilePathIsIncorrect() {
        Ballot ballot = new Ballot(TEST_ROOT_PATH + "non-existing.txt");
    }
}
