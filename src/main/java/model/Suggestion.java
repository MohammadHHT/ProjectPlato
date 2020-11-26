package model;

import java.util.ArrayList;

public class Suggestion {
    private String suggestionID;
    private String playerID;
    private String gameName;
    private static ArrayList<Suggestion> allSuggestion;

    static {
        allSuggestion = new ArrayList<Suggestion>();
    }

    public Suggestion(String playerID, String gameName) {
        this.playerID = playerID;
        this.gameName = gameName;
        //suggestionID
        allSuggestion.add(this);
    }

    public static ArrayList<Suggestion> getAllSuggestion() {
        return allSuggestion;
    }

    public String getSuggestionID() {
        return null;
    }

    public String getGameName() {
        return gameName;
    }

    public String getPlayerID() {
        return null;
    }
}
