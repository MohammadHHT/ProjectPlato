package main.back.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Player extends User {

    private static HashMap<String, Player> players;

    private int level;
    private int score;
    private double money;
    private Set<String> friends;
    private Set<String> friendRequest;
    private Set<String> favoriteGames;
    private Set<Long> suggestions;
    private ArrayList<Long> inbox;
    private Set<Long> gameLogs;
    private int wins;

    static {
        players = new HashMap<>();
    }

    public Player(String firstName, String lastName, String username, String password, String email, String phone) {
        super(firstName, lastName, username, password, email, phone);
        players.put(username, this);
        this.level = 0;
        this.score = 0;
        this.money = 0;
        this.friends = new HashSet<>();
        this.friendRequest = new HashSet<>();
        this.favoriteGames = new HashSet<>();
        this.suggestions = new HashSet<>();
        this.inbox = new ArrayList<>();
        this.gameLogs = new HashSet<>();
    }

    public static void addPlayers(ArrayList<Player> players) {
        for (Player p : players) {
            Player.players.put(p.getUsername(), p);
        }
    }

    public static HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setLevel(int offset) {
        this.level += offset;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int offset) {
        this.score += offset;
    }

    public long getScore() {
        return score;
    }

    public void setMoney(double offset) {
        this.money += offset;
    }

    public double getMoney() {
        return money;
    }

    public void addFriendRequest(String username) {
        friendRequest.add(username);
    }

    public boolean acceptFriendRequest(String username) {
        if (friendRequest.contains(username)) {
            friendRequest.remove(username);
            friends.add(username);
            return true;
        }
        return false;
    }

    public boolean declineFriendRequest(String username) {
        if (friendRequest.contains(username)) {
            friendRequest.remove(username);
            return true;
        }
        return false;
    }

    public void removeFriend(String username) {
        friends.remove(username);
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void addFavoriteGame(String favoriteGame) {
        favoriteGames.add(favoriteGame);
    }

    public Set<String> getFavoriteGames() {
        return favoriteGames;
    }

    public void addSuggestion(long suggestionID) {
        suggestions.add(suggestionID);
    }

    public Set<Long> getSuggestions() {
        return suggestions;
    }

    public void addMessage(Long messageID) {
        inbox.add(messageID);
    }

    public ArrayList<Long> getMessages() {
        return inbox;
    }

    public void addGameLog(Long logID) {
        gameLogs.add(logID);
    }

    public Set<Long> getGameLogs() {
        return gameLogs;
    }

    public void addWins() {
        this.wins++;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public String toString() {
        return super.toString() + " " + gameLogs.size() + " " + wins;
    }
}