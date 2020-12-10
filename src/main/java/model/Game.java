package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Game {
    private final static ArrayList<String> games;

    private String game;
    private int gameID;

    static {
        games = new ArrayList<>(Arrays.asList("BattleSea", "DotsAndBoxes"));
    }

    public Game(String game) {
        this.game = game;
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

    public int getGameID() {
        return gameID;
    }

    public static ArrayList<String> getGames() {
        return games;
    }
}
