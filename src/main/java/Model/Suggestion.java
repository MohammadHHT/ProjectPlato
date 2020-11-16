package Model;

public class Suggestion {
    private Player player;
    private String gameName;

    public Suggestion(Player player, String gameName) {
        this.player = player;
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public Player getPlayer() {
        return player;
    }
}
