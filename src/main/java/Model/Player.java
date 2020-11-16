package Model;

import java.util.ArrayList;

public class Player extends User {
    private int platoAge;
    private double money;
    private long score;
    private static ArrayList<Player> players;
    private ArrayList<Player> friends;
    private ArrayList<Player> friendRequest;
    private ArrayList<Game> favoriteGames;
    private ArrayList<Suggestion> suggestions;
    private ArrayList<Message> inbox;
    private GameLog gameLog;

    static {
        players = new ArrayList<Player>();
    }

    public Player(String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        super(firstname, lastname, username, password, email, phoneNumber);
        this.money = 0;
        this.score = 0;
        this.platoAge = 0;
        this.gameLog = new GameLog();
        this.friends = new ArrayList<Player>();
        this.friendRequest = new ArrayList<Player>();
        this.favoriteGames = new ArrayList<Game>();
        this.suggestions = new ArrayList<Suggestion>();
        this.inbox = new ArrayList<Message>();
    }

    public void addNewPlayer(Player player) {
        //TODO
    }

    //TODO add process to accept or decline friend

    public void addNewFriend(Player player) {
        //TODO
    }

    public void removeFriend(Player player) {
        //TODO
    }

    public void addNewSuggestion(Suggestion suggestion) {
        //TODO
    }

    public void addNewMessage(Message message) {
        //TODO
    }

    public void setFriendRequest(ArrayList<Player> friendRequest) {
        this.friendRequest = friendRequest;
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

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }

    public ArrayList<Player> getFriendRequest() {
        return friendRequest;
    }

    public ArrayList<Game> getFavoriteGames() {
        return favoriteGames;
    }
}
