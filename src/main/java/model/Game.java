package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public abstract class Game {
    private final static ArrayList<String> games;

    private long gameID;

    static {
        games = new ArrayList<>(Arrays.asList("Battle Sea", "Dots And Boxes"));
    }

    public Game() {
        gameID = IDGenerator();
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public void setScore(){
        //TODO
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
