package main.back.game;

import main.back.account.Player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLog {

    public static HashMap<Long, GameLog> gameLogs;

    private long logID;
    private String game;
    private String host;
    private String guest;
    private LocalDate date;
    private Result result;            //result of host player

    static {
        gameLogs = new HashMap<>();
    }

    public GameLog(String game, String host, String guest, Result result) {
        logID = (new Random()).nextLong();
        gameLogs.put(logID, this);
        this.game = game;
        this.host = host;
        this.guest = guest;
        this.result = result;
        date = LocalDate.now();

        if (result == Result.WIN) {
            Player.getPlayers().get(host).addWins();
            Player.getPlayers().get(guest).addDefeats();
        } else if (result == Result.DEFEAT) {
            Player.getPlayers().get(guest).addWins();
            Player.getPlayers().get(host).addDefeats();
        }
    }

    public static void addGameLogs(ArrayList<GameLog> gameLogs) {
        for (GameLog g : gameLogs) {
            GameLog.gameLogs.put(g.logID, g);
        }
    }

    public static HashMap<Long, GameLog> getGameLogs() {
        return gameLogs;
    }

    public String getGame() {
        return game;
    }

    public String getHost() {
        return host;
    }

    public String getGuest() {
        return guest;
    }

    public LocalDate getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }
}

