package controller;

import exception.ThisUserIsAlreadyYourFriendException;
import exception.UsernameNotFoundException;
import model.*;

import java.util.Map;

public class PlayerController {

    public void showPoints(String userName) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            System.out.println(Player.getPlayers().get(userName).getScore());
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void viewFavoriteGames(String userName) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            for (Game favoriteGame : Player.getPlayers().get(userName).getFavoriteGames()) {
                System.out.println(favoriteGame.getGameName());
            }
        } else {
            throw new UsernameNotFoundException();
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
        if (Player.getPlayers().get(userName).getFriends().containsKey(userNameOfIntendedUser)) {
            Player player = Player.getPlayers().get(userNameOfIntendedUser);
            System.out.println("Player name= " + player.getFirstname() + " " + player.getLastname());
            System.out.println("Plato Age= " + player.getPlatoAge());
            System.out.println("E-mail= " + player.getEmail());
            System.out.println("Score= " + player.getScore());
            //TODO show number of wins & lose
        }
    }

    public void showFriendRequests(String userName) {
        for (Map.Entry<String, Player> entry : Player.getPlayers().get(userName).getFriendRequest().entrySet()) {
            System.out.println(entry.getValue().getUsername());
        }
    }

    public void acceptRequests(String userName, String friendUserName) {
        if (Player.getPlayers().get(userName).getFriendRequest().containsKey(friendUserName)) {
            Player.getPlayers().get(userName).getFriendRequest().remove(friendUserName);
            Player.getPlayers().get(userName).getFriends().put(friendUserName, Player.getPlayers().get(friendUserName));
            Player.getPlayers().get(friendUserName).getFriends().put(userName, Player.getPlayers().get(userName));
        }
    }

    public void declineRequests(String userName, String userNameOfApplicant) {
        if (Player.getPlayers().get(userName).getFriendRequest().containsKey(userNameOfApplicant)) {
            Player.getPlayers().get(userName).getFriendRequest().remove(userNameOfApplicant);
        }
    }
}
