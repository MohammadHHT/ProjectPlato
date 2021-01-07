package game;

import account.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {

    private final static ArrayList<String> names;
    private static HashMap<Long, Game> games;

    private long gameID;
    protected Player host;
    protected Player guest;
    protected Player turn;
    protected boolean moved;

    static {
        names = new ArrayList<>(Arrays.asList("Battle Sea", "Dots And Boxes"));
        games = new HashMap<>();
    }

    public Game(Player host) {
        gameID = IDGenerator();
        games.put(gameID, this);
        this.host = host;
        turn = host;
        moved = false;
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public long getGameID() {
        return gameID;
    }

    public Player getHost() {
        return host;
    }

    public void join(Player guest) {
        this.guest = guest;
    }

    public Player getGuest() {
        return guest;
    }

    public void turn() {
        if (turn.equals(host)) {
            turn = guest;
        } else {
            turn = host;
        }
        moved = false;
    }

    public boolean isMoved() {
        return moved;
    }

    public abstract void setScore(); //TODO

    public static ArrayList<String> getNames() {
        return names;
    }

    public static HashMap<Long, Game> getGames() {
        return games;
    }

    public abstract Player judge();
}
