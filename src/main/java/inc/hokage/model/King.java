package inc.hokage.model;

public class King {
    private String name;
    private Kingdom kingdom;

    public King(String name, Kingdom kingdom){
        this.name = name;
        this.kingdom = kingdom;
        kingdom.makeKing(this);
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void sendAllegianceInvitation(String message, King toKing) {
        if(toKing.doYouAcceptOurAllegiance(message))
            kingdom.setAllegianceWith(toKing.kingdom);
    }

    private boolean doYouAcceptOurAllegiance(String message) {
        return kingdom.isMessageHonourable(message);
    }
}
