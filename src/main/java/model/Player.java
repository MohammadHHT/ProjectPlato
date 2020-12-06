package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private static HashMap<String, Player> players;

    private int platoAge;
    private double money;
    private long score;
    private HashMap<String, Player> friends;
    private HashMap<String, Player> friendRequest;
    private ArrayList<String> favoriteGames;
    private ArrayList<String> suggestions;
    private ArrayList<String> inbox;
    private ArrayList<String> gameLogs;

    static {
        players = new HashMap<>();
    }

    public Player(String firstName, String lastName, String username, String password, String email, String phoneNumber) {
        super(firstName, lastName, username, password, email, phoneNumber);
        players.put(username, this);
        this.money = 0;
        this.score = 0;
        this.platoAge = 0;
        this.friends = new HashMap<>();
        this.friendRequest = new HashMap<>();
        this.favoriteGames = new ArrayList<>();
        this.suggestions = new ArrayList<>();
        this.inbox = new ArrayList<>();
        this.gameLogs = new ArrayList<>();
    }

    //TODO add process to accept or decline friend

    public void addNewFriend(Player player) {
        //TODO
    }

    public void removeFriend(Player player) {
        friends.remove(player);
    }

    public void addNewSuggestion(Suggestion suggestion) {
        //TODO
    }

    public void addNewMessage(Message message) {
        //TODO
    }

    public static void addPlayer(Player player) {
        players.put(player.getUsername(), player);
    }

    public void setPlatoAge(int platoAge) {
        this.platoAge = platoAge;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setFavoriteGames(ArrayList<String> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public int getPlatoAge() {
        return platoAge;
    }

    public double getMoney() {
        return money;
    }

    public long getScore() {
        return score;
    }

    public static HashMap<String, Player> getPlayers() {
        return players;
    }

    public HashMap<String, Player> getFriends() {
        return friends;
    }

    public HashMap<String, Player> getFriendRequest() {
        return friendRequest;
    }

    public ArrayList<String> getFavoriteGames() {
        return favoriteGames;
    }

    public ArrayList<String> getInbox() {
        return inbox;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    public ArrayList<String> getGameLogs() {
        return gameLogs;
    }
}