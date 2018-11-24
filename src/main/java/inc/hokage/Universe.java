package inc.hokage;

import inc.hokage.model.King;
import inc.hokage.model.Kingdom;

import java.util.*;

public class Universe {

    public static final Set<Kingdom> NO_ALLIES = Collections.emptySet();

    private Set<Kingdom> kingdoms = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public Optional<King> whoIsTheRuler() {
        Optional<Kingdom> rulingKingdom = whichIsTheRulingKingdom();

        Optional<King> ruler = rulingKingdom.isPresent() ?
                Optional.of(rulingKingdom.get().getKing()) :
                Optional.empty();

        return ruler;
    }

    private Optional<Kingdom> whichIsTheRulingKingdom() {
        Optional<Kingdom> kingdomWithMaximumSupporters = kingdoms.stream().max(
                Comparator.comparingInt(Kingdom::getAlliesCount));

        if(kingdomWithMaximumSupporters.isPresent() &&
                hasMajoritySupport(kingdomWithMaximumSupporters.get()))
            return kingdomWithMaximumSupporters;
        else
            return Optional.empty();
    }

    private boolean hasMajoritySupport(Kingdom kingdomWithMaximumSupporters) {
        return kingdomWithMaximumSupporters.getAlliesCount() > kingdoms.size() / 2;
    }

    public Set<Kingdom> whoAreTheAlliesOf(King king) {
        return king.getKingdom().getAllies();
    }

    public void addKingdom(Kingdom kingdom) {
        kingdoms.add(kingdom);
    }
}
