package model;

import java.util.ArrayList;

public class GameLog {
    private int numberOfTimesPlayed;
    private int numberOfWins;
    private int numberOfDefeat;
    private int level;
    private String logID;
    private long takenScore;
    private static ArrayList<GameLog> gameLogs;

    static {
        gameLogs = new ArrayList<GameLog>();
    }

    public GameLog() {
        this.numberOfTimesPlayed = 0;
        this.numberOfWins = 0;
        this.numberOfDefeat = 0;
        this.level = 0;
        this.takenScore = 0;
        //logID
    }

    public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public void setTakenScore(long takenScore) {
        this.takenScore = takenScore;
    }

    public static void setGameLogs(ArrayList<GameLog> gameLogs) {
        GameLog.gameLogs = gameLogs;
    }

    public void setNumberOfDefeat(int numberOfDefeat) {
        this.numberOfDefeat = numberOfDefeat;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public int getNumberOfTimesPlayed() {
        return numberOfTimesPlayed;
    }

    public static ArrayList<GameLog> getGameLogs() {
        return gameLogs;
    }

    public String getLogID() {
        return null;
    }

    public long getTakenScore() {
        return takenScore;
    }

    public int getNumberOfDefeat() {
        return numberOfDefeat;
    }

    public int getLevel() {
        return level;
    }
}
