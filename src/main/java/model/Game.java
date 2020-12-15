package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {
    private final static ArrayList<String> games;

    private String game;
    private long gameID;

    static {
        games = new ArrayList<>(Arrays.asList("Battle Sea", "Dots And Boxes"));
    }

    public Game(String game) {
        this.game = game;
        gameID = IDGenerator();
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public void setScore(){
        //TODO
    }

    public void setGameName(String gameName) {
        this.game = gameName;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return game;
    }

    public long getGameID() {
        return gameID;
    }

    public static ArrayList<String> getGames() {
        return games;
    }

    public abstract void turn();

    public abstract boolean join(Player guest);

    public abstract Player judge();
}
