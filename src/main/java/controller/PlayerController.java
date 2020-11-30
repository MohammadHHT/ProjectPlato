package controller;

import exception.ThisUserIsAlreadyYourFriendException;
import exception.UsernameNotFoundException;
import model.*;

import java.util.Map;

public class PlayerController {

    public void showPoints(String userName) throws UsernameNotFoundException {
        System.out.println(Player.getPlayers().get(userName).getScore());
    }

    public void viewFavoriteGames(String userName) throws UsernameNotFoundException {
        for (Game favoriteGame : Player.getPlayers().get(userName).getFavoriteGames()) {
            System.out.println(favoriteGame.getGameName());
        }
    }

    public void viewPlatoMessages(String userName) throws UsernameNotFoundException {
        //TODO
    }

    public void viewLastPlayed(String userName) {
        //TODO
    }

    public void viewAdminSuggestions(String userName) throws UsernameNotFoundException {
        //TODO
    }

    public void addFriend(String userName, String friendUserName) throws ThisUserIsAlreadyYourFriendException {
        if (!(Player.getPlayers().get(userName).getFriends().containsKey(friendUserName))) {
            Player.getPlayers().get(userName).getFriendRequest().put(friendUserName, Player.getPlayers().get(friendUserName));
        }
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
        for (Map.Entry<String, Player> entry : Player.getPlayers().get(userName).getFriends().entrySet()) {
            System.out.println(entry.getValue().getUsername());
        }
    }

    public void removeFriend(String userName, String userNameOfApplicant) {
        if (Player.getPlayers().get(userName).getFriends().containsKey(userNameOfApplicant)) {
            Player.getPlayers().get(userName).getFriends().remove(userNameOfApplicant);
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

    public void acceptRequests(String userName, String friendUserName) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Player friend : Player.getPlayers()) {
                    if (friend.getUsername().equals(friendUserName)) {
                        friend.getFriendRequest().remove(player);
                        friend.getFriends().add(player);
                        player.getFriends().add(friend);
                        break;
                    }
                }
            }
        }
    }

    public void declineRequests(String userName, String userNameOfApplicant) {
        for (Player player : Player.getPlayers()) {
            if (player.getUsername().equals(userName)) {
                for (Player friend : Player.getPlayers()) {
                    if (friend.getUsername().equals(userNameOfApplicant)) {
                        friend.getFriendRequest().remove(player);
                        break;
                    }
                }
            }
        }
    }
}
