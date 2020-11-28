package controller;

import model.Game;
import model.Message;
import model.Player;
import model.User;

public class PlayerController {

    public long showPoints(String gameName) {
        return 0;
        //TODO
    }

    public void viewFavoriteGames(String userName) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Game favoriteGame : player.getFavoriteGames()) {
                    System.out.println(favoriteGame.getGameName());
                }
                break;
            }
        }
    }

    public void viewPlatoMessages(String userName) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Message inbox : player.getInbox()) {
                    System.out.println(inbox.getMessage());
                }
                break;
            }
        }
    }

    public void viewLastPlayed(String userName) {
        //TODO
    }

    public void viewAdminSuggestions(String userName) {
        //TODO
    }

    public String addFriend(String userName, String name) {
        return ";)";
        //TODO
    }

    public void viewPlatoStatistics(String userName) {
        //TODO
    }

    public void gameHistory(String userName) {
        //TODO
    }

    public void gameStatistics(String userName, String gameName) {
        //TODO
    }

    public void showFriends(String userName) {
        //TODO
    }

    public String removeFriend(String userName, String userNameOfApplicant) {
        return ";)";
        //TODO
    }

    public void viewUserProfile(String userName, String userNameOfIntendedUser) {
        //TODO
    }

    public void showFriendRequests(String userName) {
        //TODO
    }

    public String acceptRequests(String userName, String userNameOfApplicant) {
        return ";)";
        //TODO
    }

    public String declineRequests(String userName, String userNameOfApplicant) {
        return ";)";
        //TODO
    }
}
