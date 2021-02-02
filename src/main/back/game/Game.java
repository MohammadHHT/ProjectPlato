package main.back.game;

import main.back.account.Player;
import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {

    private static HashMap<Long, Game> games;

    private long gameID;
    protected Player host;
    protected WebSocket hostSocket;
    protected Player guest;
    protected WebSocket guestSocket;
    protected Player turn;

    static {
        games = new HashMap<>();
    }

    public Game(Player host, WebSocket hostSocket) {
        gameID = (new Random()).nextLong();
        games.put(gameID, this);
        this.host = host;
        this.hostSocket = hostSocket;
        turn = host;
    }

    public Player getHost() {
        return host;
    }

    public WebSocket getHostSocket() {
        return hostSocket;
    }

    public Player getGuest() {
        return guest;
    }

    public WebSocket getGuestSocket() {
        return guestSocket;
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

    public abstract void join(Player guest, WebSocket guestSocket);
}
