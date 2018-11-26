package inc.hokage;

import inc.hokage.model.King;
import inc.hokage.model.Kingdom;

import java.util.*;

public class Universe {

    private Set<Kingdom> kingdoms = new HashSet<>();

    public Optional<King> whoIsTheRuler() {
        Optional<Kingdom> rulingKingdom = whichIsTheRulingKingdom();

        Optional<King> ruler = rulingKingdom.isPresent() ?
                Optional.of(rulingKingdom.get().getKing()) :
                Optional.empty();

        return ruler;
    }

    private Optional<Kingdom> whichIsTheRulingKingdom() {
        Optional<King> kingWithMaximumSupporters = kingdoms.stream().map(Kingdom::getKing).max(
                Comparator.comparingInt(King::alliesCount));

        if(kingWithMaximumSupporters.isPresent() &&
                hasMajoritySupport(kingWithMaximumSupporters.get()))
            return Optional.of(kingWithMaximumSupporters.get().getKingdom());
        else
            return Optional.empty();
    }

    private boolean hasMajoritySupport(King kingWithMaximumSupporters) {
        return kingWithMaximumSupporters.alliesCount() > kingdoms.size() / 2;
    }

    public Set<Kingdom> whoAreTheAlliesOf(King king) {
        return king.getAllies();
    }

    public void addKingdom(Kingdom kingdom) {
        kingdoms.add(kingdom);
    }
}
