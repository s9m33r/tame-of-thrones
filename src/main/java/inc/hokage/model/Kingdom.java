package inc.hokage.model;

import java.util.HashSet;
import java.util.Set;

public class Kingdom {

    private String name;
    private String emblem;
    private King king;
    private Set<Kingdom> allies = new HashSet<>();

    public Kingdom(String name, String emblem){
        this.name = name;
        this.emblem = emblem;
    }

    public King getKing() {
        return king;
    }

    public void makeKing(King king) {
        this.king = king;
    }

    public void setAllegianceWith(Kingdom kingdom) {
        allies.add(kingdom);
        kingdom.addAlly(this);
    }

    private void addAlly(Kingdom kingdom) {
        allies.add(kingdom);
    }

    public Set<Kingdom> getAllies() {
        return allies;
    }

    public int getAlliesCount() {
        return allies.size();
    }

    public boolean isMessageHonourable(String message) {
        return true;
    }
}
