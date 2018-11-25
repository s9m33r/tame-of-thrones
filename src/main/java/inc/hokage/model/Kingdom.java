package inc.hokage.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

public class Kingdom {

    private String name;
    private String emblem;
    private Map<Character, Integer> emblemCharactersCountMap;
    private King king;

    public Kingdom(String name, String emblem){
        this.name = name;
        this.emblem = emblem;

        emblemCharactersCountMap = buildCharactersCountMap(emblem);
    }

    public King getKing() {
        return king;
    }

    public void makeKing(King king) {
        this.king = king;
    }

    public boolean isMessageHonourable(String message) {
        Map<Character, Integer> messageCharacterCountMap = buildCharactersCountMap(message);

        Optional<Character> violatingCharacter = emblemCharactersCountMap.
                keySet().stream().
                filter(character->
                isViolatingCharacter(messageCharacterCountMap, character)).
                findAny();

        return !violatingCharacter.isPresent();
    }

    private Map<Character, Integer> buildCharactersCountMap(String emblem) {
        Map<Character, Integer> characterCountMap = new HashMap<>();

        for(char character: emblem.toLowerCase().toCharArray()){
            characterCountMap.putIfAbsent(character, 0);

            characterCountMap.put(character, characterCountMap.get(character) + 1);
        }

        return characterCountMap;
    }

    private boolean isViolatingCharacter(Map<Character, Integer> messageCharacterCountMap, Character character) {
        return isNull(messageCharacterCountMap.get(character)) ||
        emblemCharactersCountMap.get(character) >
        messageCharacterCountMap.get(character);
    }
}
