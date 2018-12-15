package inc.hokage.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Ballot {
    private Random random;
    private List<String> messages;

    public Ballot(String messageFilePath) {
        random = new Random();
        try {
            messages = createInitializedMessageList(messageFilePath);
        } catch (IOException e){
            throw new BallotInitializationException(messageFilePath, e);
        }
    }

    private List<String> createInitializedMessageList(String messageFilePath) throws IOException {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(messageFilePath))) {
            return bufferedReader.lines().map(String::trim).collect(Collectors.toList());
        }
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String drawMessage() {
        return messages.get(random.nextInt(messages.size()));
    }

    public static class BallotInitializationException extends RuntimeException {
        private static final String MESSAGE_FORMAT = "Unable to initialize " +
                "ballot messages from file: %s";

        public BallotInitializationException(String messageFilePath, IOException e) {
            super(String.format(MESSAGE_FORMAT, messageFilePath), e);
        }
    }
}
