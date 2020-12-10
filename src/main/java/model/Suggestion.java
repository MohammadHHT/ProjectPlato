package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Suggestion {
    private static HashMap<Long, Suggestion> suggestions;

    private long suggestionID;
    private String game;
    private String player;

    static {
        suggestions = new HashMap<>();
    }

    public Suggestion(Player player, String game) {
        suggestionID = IDGenerator();
        suggestions.put(suggestionID, this);
        this.game = game;
        this.player = player.getUsername();
        player.addSuggestion(this.suggestionID);
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

    public long getSuggestionID() {
        return suggestionID;
    }

    public String getGame() {
        return game;
    }

    public String getPlayer() {
        return player;
    }

    public static HashMap<Long, Suggestion> getSuggestions() {
        return suggestions;
    }
}
