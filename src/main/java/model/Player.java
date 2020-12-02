package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private int platoAge;
    private double money;
    private long score;
    private static HashMap<String, Player> players;
    private HashMap<String, Player> friends;
    private HashMap<String, Player> friendRequest;
    private ArrayList<Game> favoriteGames;
    private ArrayList<String> suggestions;
    private ArrayList<String> inbox;
    private ArrayList<String> gameLogs;

    static {
        players = new HashMap<String, Player>();
    }

    public Player(String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        super(firstname, lastname, username, password, email, phoneNumber);
        this.money = 0;
        this.score = 0;
        this.platoAge = 0;
        this.friends = new HashMap<String, Player>();
        this.friendRequest = new HashMap<String, Player>();
        this.favoriteGames = new ArrayList<Game>();
        this.suggestions = new ArrayList<String>();
        this.inbox = new ArrayList<String>();
        this.gameLogs = new ArrayList<String>();
    }



    public void addNewPlayer(Player player) {
        //TODO
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

    public void setPlatoAge(int platoAge) {
        this.platoAge = platoAge;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setFavoriteGames(ArrayList<Game> favoriteGames) {
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

    public ArrayList<Game> getFavoriteGames() {
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
