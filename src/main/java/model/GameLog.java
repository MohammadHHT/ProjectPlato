package model;

import java.util.ArrayList;
import java.util.HashMap;

public class GameLog {
    private int numberOfTimesPlayed;
    private int numberOfWins;
    private int numberOfDefeat;
    private int level;
    private int logID;
    private long takenScore;
    private static HashMap<Integer, GameLog> allGameLogs;

    static {
        allGameLogs = new HashMap<Integer, GameLog>();
    }

    public GameLog() {
        this.numberOfTimesPlayed = 0;
        this.numberOfWins = 0;
        this.numberOfDefeat = 0;
        this.level = 0;
        this.takenScore = 0;
        //logID
        allGameLogs.put(logID, this);
    }

    public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public void setTakenScore(long takenScore) {
        this.takenScore = takenScore;
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

    public static HashMap<Integer, GameLog> getAllGameLogs() {
        return allGameLogs;
    }

    public int getLogID() {
        return 0;
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
