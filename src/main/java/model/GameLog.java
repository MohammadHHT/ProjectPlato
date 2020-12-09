package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLog {
    private static HashMap<Long, GameLog> gameLogs;

    private long logID;
    private String game;
    private static int numberOfTimesPlayed;
    private static int numberOfWins;
    private static int numberOfDefeats;
    private long takenScore;

    static {
        gameLogs = new HashMap<>();
        numberOfTimesPlayed = 0;
        numberOfWins = 0;
        numberOfDefeats = 0;
    }

    public GameLog(String game) {
        logID = IDGenerator();
        gameLogs.put(logID, this);
        this.game = game;
        this.takenScore = 0;
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public static void addGameLogs(ArrayList<GameLog> gameLogs) {
        for (GameLog g : gameLogs) {
            GameLog.gameLogs.put(g.logID, g);
        }
    }

    public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setTakenScore(long takenScore) {
        this.takenScore = takenScore;
    }

    public void setNumberOfDefeat(int numberOfDefeat) {
        this.numberOfDefeats = numberOfDefeat;
    }

    public String getGame() {
        return game;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public int getNumberOfTimesPlayed() {
        return numberOfTimesPlayed;
    }

    public long getLogID() {
        return logID;
    }

    public long getTakenScore() {
        return takenScore;
    }

    public int getNumberOfDefeat() {
        return numberOfDefeats;
    }

    public static HashMap<Long, GameLog> getGameLogs() {
        return gameLogs;
    }
}
