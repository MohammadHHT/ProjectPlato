package main.back.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Suggestion {

    private static HashMap<Long, Suggestion> suggestions;

    private long suggestionID;
    private String game;
    private String player;
    private LocalDateTime date;

    static {
        suggestions = new HashMap<>();
    }

    public Suggestion(Player player, String game) {
        suggestionID = (new Random()).nextLong();
        suggestions.put(suggestionID, this);
        this.game = game;
        this.player = player.getUsername();
        date = LocalDateTime.now();
        player.addSuggestion(this.suggestionID);
    }

    public static void addSuggestions(ArrayList<Suggestion> suggestions) {
        for (Suggestion s : suggestions) {
            Suggestion.suggestions.put(s.suggestionID, s);
        }
    }

    public static HashMap<Long, Suggestion> getSuggestions() {
        return suggestions;
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
}
