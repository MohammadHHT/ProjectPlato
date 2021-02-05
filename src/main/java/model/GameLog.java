package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLog {
    public enum Result {
        WIN, DRAW, DEFEAT
    }

    private static HashMap<Long, GameLog> gameLogs;

    private long logID;
    private String game;
    private String host;
    private LocalDate date;
    private Result result;            //result of host player

    static {
        gameLogs = new HashMap<>();
    }

    public GameLog(String game, Player host, Player guest, LocalDate date, Result result) {
        logID = IDGenerator();
        gameLogs.put(logID, this);
        this.game = game;
        this.host = host.getUsername();
        this.date = date;
        this.result = result;

        switch (result) {
            case WIN:
                host.setScore(host.getScore() + 10);
                host.setLevel(host.getLevel() + 1);
                if (host.getWins().containsKey(game)) {
                    host.getWins().put(game, host.getWins().get(game) + 1);
                } else {
                    host.getWins().put(game, 1);
                }

                guest.setScore(host.getScore() - 5);
                if (guest.getDefeats().containsKey(game)) {
                    guest.getDefeats().put(game, guest.getDefeats().get(game) + 1);
                } else {
                    guest.getDefeats().put(game, 1);
                }
                break;
            case DEFEAT:

                guest.setScore(host.getScore() + 10);
                guest.setLevel(host.getLevel() + 1);
                if (guest.getWins().containsKey(game)) {
                    guest.getWins().put(game, guest.getWins().get(game) + 1);
                } else {
                    guest.getWins().put(game, 1);
                }

                host.setScore(host.getScore() - 5);
                if (host.getDefeats().containsKey(game)) {
                    host.getDefeats().put(game, host.getDefeats().get(game) + 1);
                } else {
                    host.getDefeats().put(game, 1);
                }
                break;
        }
        if (host.getPlays().containsKey(game)) {
            host.getPlays().put(game, host.getPlays().get(game) + 1);
        } else {
            host.getPlays().put(game, 1);
        }
        if (guest.getPlays().containsKey(game)) {
            guest.getPlays().put(game, guest.getPlays().get(game) + 1);
        } else {
            guest.getPlays().put(game, 1);
        }
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

    public String getGame() {
        return game;
    }

    public String getHost() {
        return host;
    }

    public LocalDate getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }

    public static HashMap<Long, GameLog> getGameLogs() {
        return gameLogs;
    }
}
