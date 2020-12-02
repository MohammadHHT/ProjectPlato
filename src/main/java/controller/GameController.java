package controller;

import exception.GameNotFoundException;
import exception.UsernameNotFoundException;
import model.Game;
import model.GameLog;
import model.Player;

public class GameController {

    public void showScoreboard(String gameName) {
        //TODO
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

    public void addToFavorites(String userName, String gameName) throws GameNotFoundException {
        for (Game game : Game.getGames()) {
            if (game.getGameName().equals(gameName)) {
                if (Player.getPlayers().containsKey(userName)) {
                    Player.getPlayers().get(userName).getFavoriteGames().add(game);
                }
            } else
                throw new GameNotFoundException();
        }
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
