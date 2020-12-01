package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private String gameName;
    private int gameID;
    private static HashMap<Integer, Game> games;
    private static ArrayList<String> gamesName;

    static {
        games = new HashMap<Integer, Game>();
        gamesName = new ArrayList<String>();
    }

    public Game(String gameName, String gameID) {
        this.gameName = gameName;
        //gameID
        gamesName.add(gameName);
    }

    public void run(){
        //TODO
    }

    public void setScore(){
        //TODO
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public static HashMap<Integer, Game> getGames() {
        return games;
    }

    public static ArrayList<String> getGamesName() {
        return gamesName;
    }
}
