package Model;

import java.util.ArrayList;

public class GameLog {
    private int numberOfTimesPlayed;
    private int numberOfWins;
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
}
