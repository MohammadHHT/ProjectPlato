package main.back.game;

import main.back.account.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {

    private static HashMap<Long, Game> games;

    private long gameID;
    protected Player host;
    protected Player guest;
    protected Player turn;

    static {
        games = new HashMap<>();
    }

    public Game(Player host) {
        gameID = (new Random()).nextLong();
        games.put(gameID, this);
        this.host = host;
        turn = host;
    }

    public Player getHost() {
        return host;
    }

    public Player getGuest() {
        return guest;
    }

    public Player getTurn() {
        return turn;
    }

    public void turn() {
        if (turn.equals(host)) {
            turn = guest;
        } else {
            turn = host;
        }
    }

    public static HashMap<Long, Game> getGames() {
        return games;
    }

    public abstract Player judge();

    public abstract void join(Player guest);
}
