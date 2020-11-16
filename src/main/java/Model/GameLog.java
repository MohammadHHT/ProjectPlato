package Model;

import java.util.ArrayList;

public class GameLog {
    private int numberOfTimesPlayed;
    private int numberOfWins;
    private int numberOfDefeat;
    private int level;
    private long logID;
    private long takenScore;
    private static ArrayList<GameLog> gameLogs;

    //TODO Constructor

    public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setLogID(long logID) {
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

    public long getLogID() {
        return logID;
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
