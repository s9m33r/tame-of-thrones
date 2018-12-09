package inc.hokage.model;

import java.util.HashSet;
import java.util.Set;

public class King {
    private String name;
    private Kingdom kingdom;
    private Set<Kingdom> allies = new HashSet<>();

    public King(String name, Kingdom kingdom){
        this.name = name;
        this.kingdom = kingdom;
        kingdom.makeKing(this);
    }

    public String getName() {
        return name;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public Set<Kingdom> getAllies() {
        return allies;
    }

    public int alliesCount() {
        return allies.size();
    }

    public void sendAllegianceInvitation(String message, King toKing) {
        if(toKing.willYouPledgeYourAllegiance(message, this))
            setAllegianceWith(toKing.kingdom);
    }

    public void sendAllegianceInvitation(String message, Kingdom toKingdom){
        if(toKingdom.willPledgeAllegiance(message, this))
            allies.add(toKingdom);
    }

    private boolean willYouPledgeYourAllegiance(String message, King fromKing) {
        if(kingdom.isMessageHonourable(message)) {
            setAllegianceWith(fromKing.kingdom);
            return true;
        }else
            return false;
    }

    private void setAllegianceWith(Kingdom kingdom) {
        allies.add(kingdom);
    }

    @Override
    public String toString() {
        return "King{" +
                "name='" + name + '\'' +
                '}';
    }
}
