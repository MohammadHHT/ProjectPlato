package controller;

import exception.*;
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

    public void addFavoriteGame(String userName, String gameName) throws UsernameNotFoundException, GameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            if (Game.getGames().containsKey(Integer.parseInt(gameName))) {
                Player.getPlayers().get(userName).getFavoriteGames().add(Game.getGames().get(Integer.parseInt(gameName)));
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void joinEvent(String userName, String eventID, String gameName) throws EventIDNotFoundException {
        if (Event.getEvents().containsKey(Integer.parseInt(eventID))) {
            GameController.runGameForEvent(userName, Event.getEvents().get(Integer.parseInt(eventID)).getEventScore(),
                    gameName);
        } else {
            throw new EventIDNotFoundException();
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

    public void addFriend(String userName, String friendUserName) throws UsernameNotFoundException, ThisUserIsAlreadyYourFriendException {
        if (Player.getPlayers().containsKey(userName) && Player.getPlayers().containsKey(friendUserName)) {
            if (!(Player.getPlayers().get(userName).getFriends().containsKey(friendUserName))) {
                Player.getPlayers().get(userName).getFriendRequest().put(friendUserName, Player.getPlayers().get(friendUserName));
            } else {
                throw new ThisUserIsAlreadyYourFriendException();
            }
        } else {
            throw new UsernameNotFoundException();
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

    public void showFriends(String userName) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(userName).getFriends().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void removeFriend(String userName, String userNameOfApplicant) throws UsernameNotFoundException, ThisUserIsNotYourFriendException {
        if (Player.getPlayers().containsKey(userName) && Player.getPlayers().containsKey(userNameOfApplicant)) {
            if (Player.getPlayers().get(userName).getFriends().containsKey(userNameOfApplicant)) {
                Player.getPlayers().get(userName).getFriends().remove(userNameOfApplicant);
            } else {
                throw new ThisUserIsNotYourFriendException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void viewUserProfile(String userName, String userNameOfIntendedUser) throws UsernameNotFoundException, ThisUserIsNotYourFriendException {
        if (Player.getPlayers().containsKey(userName) && Player.getPlayers().containsKey(userNameOfIntendedUser)) {
            if (Player.getPlayers().get(userName).getFriends().containsKey(userNameOfIntendedUser)) {
                Player player = Player.getPlayers().get(userNameOfIntendedUser);
                System.out.println("Player name= " + player.getFirstname() + " " + player.getLastname());
                System.out.println("Plato Age= " + player.getPlatoAge());
                System.out.println("E-mail= " + player.getEmail());
                System.out.println("Score= " + player.getScore());
                //TODO show number of wins & lose
            } else {
                throw new ThisUserIsNotYourFriendException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void showFriendRequests(String userName) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(userName)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(userName).getFriendRequest().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void acceptRequests(String userName, String friendUserName) throws UsernameNotFoundException, RequestNotFoundException {
        if (Player.getPlayers().containsKey(userName) && Player.getPlayers().containsKey(friendUserName)) {
            if (Player.getPlayers().get(userName).getFriendRequest().containsKey(friendUserName)) {
                Player.getPlayers().get(userName).getFriendRequest().remove(friendUserName);
                Player.getPlayers().get(userName).getFriends().put(friendUserName, Player.getPlayers().get(friendUserName));
                Player.getPlayers().get(friendUserName).getFriends().put(userName, Player.getPlayers().get(userName));
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void declineRequests(String userName, String userNameOfApplicant) throws UsernameNotFoundException, RequestNotFoundException {
        if (Player.getPlayers().containsKey(userName) && Player.getPlayers().containsKey(userNameOfApplicant)) {
            if (Player.getPlayers().get(userName).getFriendRequest().containsKey(userNameOfApplicant)) {
                Player.getPlayers().get(userName).getFriendRequest().remove(userNameOfApplicant);
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }
}
