package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Suggestion {
    private int suggestionID;
    private String playerID;
    private String gameName;
    private static HashMap<Integer, Suggestion> allSuggestion;

    static {
        allSuggestion = new HashMap<Integer, Suggestion>();
    }

    public Suggestion(String playerID, String gameName) {
        this.playerID = playerID;
        this.gameName = gameName;
        //suggestionID
        allSuggestion.put(suggestionID, this);
    }

    public static HashMap<Integer, Suggestion> getAllSuggestion() {
        return allSuggestion;
    }

    public int getSuggestionID() {
        return 0;
    }

    public String getGameName() {
        return gameName;
    }

    public String getPlayerID() {
        return null;
    }
}
