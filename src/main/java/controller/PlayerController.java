package controller;

import model.*;

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
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Suggestion suggestion : player.getSuggestions()) {
                    System.out.println(suggestion.getGameName());
                }
                break;
            }
        }
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
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Player friend : player.getFriends()) {
                    System.out.println(friend.getUsername());
                }
            }
        }
    }

    public void removeFriend(String userName, String userNameOfApplicant) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userNameOfApplicant)) {
                for (Player friend : Player.getPlayers()) {
                    if (friend.getUsername().equals(userName)) {
                        player.removeFriend(friend);
                        break;
                    }
                }
            }
        }
    }

    public void viewUserProfile(String userName, String userNameOfIntendedUser) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                System.out.println("Player name= " + player.getFirstname() + " " + player.getLastname());
                System.out.println("Plato Age= " + player.getPlatoAge());
                System.out.println("E-mail= " + player.getEmail());
                System.out.println("Score= " + player.getScore());
                //TODO show number of wins & lose
            }
        }
    }

    public void showFriendRequests(String userName) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Player friendshipSeeker : player.getFriendRequest()) {
                    System.out.println(friendshipSeeker.getUsername());
                }
                break;
            }
        }
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
