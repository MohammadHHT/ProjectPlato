package controller;

import exception.GameNotFoundException;
import exception.UsernameNotFound;
import model.GameLog;
import model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GameController {

    public void showScoreboard(String gameName) throws GameNotFoundException {
        HashMap<String, Long> allPlayersScore = new HashMap<String, Long>();
        if (gameName.equalsIgnoreCase("Dots And Boxes")) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().entrySet()) {
                allPlayersScore.put(entry.getValue().getUsername(),
                        GameLog.getGameLogs().get(Integer.parseInt(entry.getValue().getGameLogs().get(0))).getTakenScore());
            }
        } else if (gameName.equalsIgnoreCase("Sea Battle")) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().entrySet()) {
                allPlayersScore.put(entry.getValue().getUsername(),
                        GameLog.getGameLogs().get(Integer.parseInt(entry.getValue().getGameLogs().get(1))).getTakenScore());
            }
        } else {
            throw new GameNotFoundException();
        }
        TreeMap<String, Long> sorted = new TreeMap<>();
        sorted.putAll(allPlayersScore);
        sorted.entrySet().stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .forEach(n -> {
                    System.out.println(n.getKey() + " " + n.getValue());
                });
    }

    public void details(String gameName) {
        //TODO
    }

    public void showLog(String userName, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (gameName.equalsIgnoreCase("Dots And Boxes")) {
            if (Player.getPlayers().containsKey(userName)) {
                System.out.println("Level: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getLevel() + "\n" +
                        "Score: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getTakenScore() + "\n" +
                        "Wins: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfWins() + "\n" +
                        "Defeats: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfDefeats() + "\n" +
                        "Number of played: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfPlays());
            } else {
                throw new UsernameNotFound();
            }
        } else if (gameName.equalsIgnoreCase("Sea Battle")) {
            if (Player.getPlayers().containsKey(userName)) {
                System.out.println("Level: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getLevel() + "\n" +
                        "Score: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getTakenScore() + "\n" +
                        "Wins: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfWins() + "\n" +
                        "Defeats: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfDefeats() + "\n" +
                        "Number of played: " + GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfPlays());
            } else {
                throw new UsernameNotFound();
            }
        } else {
            throw new GameNotFoundException();
        }
    }

    public void showWinsCount(String userName, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            if (gameName.equalsIgnoreCase("Dots And Boxes")) {
                System.out.println("Number of Wins in " + gameName + " game: " +
                        GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().get(userName)
                                .getGameLogs().get(0))).getNumberOfWins());
            } else if (gameName.equalsIgnoreCase("Sea Battle")) {
                System.out.println("Number of Wins in " + gameName + " game: " +
                        GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().get(userName)
                                .getGameLogs().get(1))).getNumberOfWins());
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void showPlayedCount(String userName, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            if (gameName.equalsIgnoreCase("Dots And Boxes")) {
                System.out.println("Number of played in " + gameName + " game: " +
                        GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().get(userName)
                                .getGameLogs().get(0))).getNumberOfPlays());
            } else if (gameName.equalsIgnoreCase("Sea Battle")) {
                System.out.println("Number of played in " + gameName + " game: " +
                        GameLog.getGameLogs().get(Integer.parseInt(Player.getPlayers().get(userName)
                                .getGameLogs().get(1))).getNumberOfPlays());
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public static void runGame(String userName, String gameName) {
        //TODO
    }

    public static void runGameForEvent(String userName, long EventScore, String gameName) {
        //TODO
    }

    public void showPoints(String userName, String logID) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(userName)) {
            if (Player.getPlayers().get(userName).getGameLogs().contains(logID)) {
                System.out.println(GameLog.getGameLogs().get(Integer.parseInt(logID)).getTakenScore());
            }
        } else
            throw new UsernameNotFound();
    }
}
