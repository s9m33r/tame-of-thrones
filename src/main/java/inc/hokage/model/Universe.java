package inc.hokage.model;

import inc.hokage.model.King;
import inc.hokage.model.Kingdom;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class Universe {
    private Set<Kingdom> kingdoms = new HashSet<>();

    public Universe(){}

    public Universe(String filePathToInitKingdoms) throws IOException {
        List<String> kingdomsEntries = FileUtils.readLines(
                new File(filePathToInitKingdoms), Charset.defaultCharset());

        kingdomsEntries.forEach(kingdomsEntry -> {
            String[] splicedEntry = kingdomsEntry.split(",");
            if (splicedEntry.length == 3) {
                Kingdom kingdom = new Kingdom(splicedEntry[0], splicedEntry[1]);
                King king = new King(splicedEntry[2], kingdom);
                kingdom.makeKing(king);
                addKingdom(kingdom);
            }
        });
    }

    public Optional<Kingdom> getKingdomByName(String name) {
        return kingdoms.stream().
                filter(kingdom -> kingdom.getName().equalsIgnoreCase(name)).
                findFirst();
    }

    public void addKingdom(Kingdom kingdom) {
        kingdoms.add(kingdom);
    }

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

        if (kingWithMaximumSupporters.isPresent() &&
                hasMajoritySupport(kingWithMaximumSupporters.get()))
            return Optional.of(kingWithMaximumSupporters.get().getKingdom());
        else
            return Optional.empty();
    }

    private boolean hasMajoritySupport(King kingWithMaximumSupporters) {
        return kingWithMaximumSupporters.alliesCount() >= kingdoms.size() / 2;
    }

    public Set<Kingdom> whoAreTheAlliesOf(King king) {
        return king.getAllies();
    }
}
