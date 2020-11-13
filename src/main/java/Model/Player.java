package Model;

import java.util.ArrayList;
import java.util.Date;

public class Player extends User {
    private Date platoAge;
    private double money;
    private long score;
    private static ArrayList<Player> players;
    private ArrayList<Player> friends;
    private ArrayList<Player> friendRequest;
    //TODO gamesLog

    static {
        players = new ArrayList<Player>();
    }

    public Player(String firstname, String lastname, String username, long userID, String password, String email, String phoneNumber) {
        super(firstname, lastname, username, userID, password, email, phoneNumber);
        this.money = 0;
        this.score = 0;
        this.friends = new ArrayList<Player>();
        this.friendRequest = new ArrayList<Player>();
    }
}
