package controller;

import exception.GameNotFoundException;
import exception.UsernameNotFoundException;
import model.GameLog;
import model.Player;

import java.util.HashMap;
import java.util.Map;

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
        //TODO sorting
    }

    public void details(String gameName) {
        //TODO
    }

    public void showLog(String gameName) {
        //TODO
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
