package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Suggestion {
    private static HashMap<Long, Suggestion> suggestions;

    private long suggestionID;
    private String gameName;

    static {
        suggestions = new HashMap<>();
    }

    public Suggestion(String username, String gameName) {
        suggestionID = IDGenerator();
        suggestions.put(suggestionID, this);
        this.gameName = gameName;
        ((Player) User.getUsers().get(username)).addSuggestion(this.suggestionID);
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public static void addSuggestions(ArrayList<Suggestion> suggestions) {
        for (Suggestion s : suggestions) {
            Suggestion.suggestions.put(s.suggestionID, s);
        }
    }

    public int getSuggestionID() {
        return 0;
    }

    public String getGameName() {
        return gameName;
    }

    public static HashMap<Long, Suggestion> getSuggestions() {
        return suggestions;
    }
}
