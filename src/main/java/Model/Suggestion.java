package Model;

import java.util.ArrayList;

public class Suggestion {
    private long suggestionID;
    private Player player;
    private String gameName;
    private static ArrayList<Suggestion> allSuggestion;

    static {
        allSuggestion = new ArrayList<Suggestion>();
    }

    public Suggestion(Player player, String gameName) {
        this.player = player;
        this.gameName = gameName;
        //suggestionID
    }

    public void addNewSuggestion(Suggestion suggestion) {
        //TODO
    }

    public static ArrayList<Suggestion> getAllSuggestion() {
        return allSuggestion;
    }

    public long getSuggestionID() {
        return suggestionID;
    }

    public String getGameName() {
        return gameName;
    }

    public Player getPlayer() {
        return player;
    }
}
