package model;

import java.util.ArrayList;

public class Game {
    private String gameName;
    private int gameID;
    private static ArrayList<Game> games;

    static {
        games = new ArrayList<Game>();
    }

    public Game(String gameName, String gameID) {
        this.gameName = gameName;
        //gameID
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

    public static void setGames(ArrayList<Game> games) {
        Game.games = games;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }
}
