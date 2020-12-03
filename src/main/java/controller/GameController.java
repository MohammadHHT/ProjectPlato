package controller;

import exception.GameNotFoundException;
import exception.UsernameNotFoundException;
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
                        GameLog.getAllGameLogs().get(Integer.parseInt(entry.getValue().getGameLogs().get(0))).getTakenScore());
            }
        } else if (gameName.equalsIgnoreCase("Sea Battle")) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().entrySet()) {
                allPlayersScore.put(entry.getValue().getUsername(),
                        GameLog.getAllGameLogs().get(Integer.parseInt(entry.getValue().getGameLogs().get(1))).getTakenScore());
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

    public void showLog(String userName, String gameName) {
        if (gameName.equalsIgnoreCase("Dots And Boxes")) {
            if (Player.getPlayers().containsKey(userName)) {
                System.out.println("Level: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getLevel() + "\n" +
                        "Score: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getTakenScore() + "\n" +
                        "Wins: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfWins() + "\n" +
                        "Defeats: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfDefeat() + "\n" +
                        "Number of played: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(0))).getNumberOfTimesPlayed());
            }
        } else if (gameName.equalsIgnoreCase("Sea Battle")) {
            if (Player.getPlayers().containsKey(userName)) {
                System.out.println("Level: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getLevel() + "\n" +
                        "Score: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getTakenScore() + "\n" +
                        "Wins: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfWins() + "\n" +
                        "Defeats: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfDefeat() + "\n" +
                        "Number of played: " + GameLog.getAllGameLogs().get(Integer.parseInt(Player.getPlayers().
                        get(userName).getGameLogs().get(1))).getNumberOfTimesPlayed());
            }
        }
    }

    public int showWinsCount(String gameName) {
        return 0;
        //TODO
    }

    public int showPlayedCount(String gameName) {
        return 0;
        //TODO
    }

    public void runGame(String gameName) {
        //TODO
    }


    public static void runGameForEvent(String userName, long EventScore, String gameName) {
        //TODO
    }

    public static void runGameFromSuggestion(String userName, String gameName) {
        //TODO
    }

    public void showPoints(String userName, String logID) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            if (Player.getPlayers().get(userName).getGameLogs().contains(logID)) {
                System.out.println(GameLog.getAllGameLogs().get(Integer.parseInt(logID)).getTakenScore());
            }
        } else
            throw new UsernameNotFoundException();
    }
}
