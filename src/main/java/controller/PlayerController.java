package controller;

import exception.*;
import model.*;

import java.util.ArrayList;

public class PlayerController {
    private static final PlayerController playerController = new PlayerController();

    private PlayerController() {
    }

    public static PlayerController getPlayerController() {
        return playerController;
    }

    public String showPoints(String username) {
        return Long.toString(Player.getPlayers().get(username).getScore());
    }

    public String showFavoriteGames(String username) {
        ArrayList<String> favoriteGames = Player.getPlayers().get(username).getFavoriteGames();
        StringBuilder tmp = new StringBuilder();
        for (String s : favoriteGames) {
            tmp.append(s).append('\n');
        }
        return tmp.toString().trim();
    }

    public String showAdminMessages(String username) {
        StringBuilder tmp = new StringBuilder();
        for (String s : Player.getPlayers().get(username).getInbox()) {
            tmp.append(s).append('\n');
        }
        return tmp.toString().trim();
    }

    public String showAdminSuggestions(String username) {
        ArrayList<Long> suggestions = Player.getPlayers().get(username).getSuggestions();
        StringBuilder tmp = new StringBuilder();
        for (Long l : suggestions) {
            tmp.append(Suggestion.getSuggestions().get(l).getGame()).append('\n');
        }
        return tmp.toString().trim();
    }

    public void playSuggested(String username, String game) throws GameNotFound {
        if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
            //TODO
        } else {
            throw new GameNotFound();
        }
    }

    public String showLastGame(String username) {
        Player player = Player.getPlayers().get(username);
        int size = player.getGameLogs().size();
        return size == 0 ? "" : GameLog.getGameLogs().get(player.getGameLogs().get(size - 1)).getGame();
    }

    public void addFriend(String username, String friend) throws AlreadyYourFriend, UsernameNotFound {
        Player player = Player.getPlayers().get(username);
        if (player != null) {
            if (!player.getFriends().contains(friend)) {
                player.getFriends().add(friend);
            } else {
                throw new AlreadyYourFriend();
            }
        } else {
            throw new UsernameNotFound();
        }
    }
}
