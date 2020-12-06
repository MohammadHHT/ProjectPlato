package controller;

import exception.*;
import model.*;

import java.util.ArrayList;
import java.util.Map;

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
        String tmp = "";
        for (String s : favoriteGames) {
            tmp += s + " ";
        }
        return tmp.trim();
    }

    public String showAdminMessages(String username) {
        String tmp = "";
        for (String inbox : Player.getPlayers().get(username).getInbox()) {
            tmp += inbox + " ";
        }
        return tmp.trim();
    }

    public void showLastPlayed(String username) {
        Player player = Player.getPlayers().get(username);
        if (player.getGameLogs().get(player.getGameLogs().size() - 1));
    }

    public void addFavoriteGame(String username, String gameName) throws UsernameNotFoundException, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().containsKey(Integer.parseInt(gameName))) {
                Player.getPlayers().get(username).getFavoriteGames().add(Game.getGames().get(Integer.parseInt(gameName)));
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void deleteFavoriteGame(String username, String gameName) throws UsernameNotFoundException, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().containsKey(Integer.parseInt(gameName))) {
                Player.getPlayers().get(username).getFavoriteGames().remove(Game.getGames().get(Integer.parseInt(gameName)));
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void joinEvent(String username, String eventID, String gameName) throws EventIDNotFoundException {
        if (Event.getEvents().containsKey(Integer.parseInt(eventID))) {
            GameController.runGameForEvent(username, Event.getEvents().get(Integer.parseInt(eventID)).getEventScore(),
                    gameName);
        } else {
            throw new EventIDNotFoundException();
        }

    }

    public void playSuggestedGame(String username, String suggestionID) throws UsernameNotFoundException, SuggestionIDNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Suggestion.getAllSuggestion().containsKey(Integer.parseInt(suggestionID))) {
                GameController.runGame(Player.getPlayers().get(username).getUsername(),
                        Suggestion.getAllSuggestion().get(Integer.parseInt(suggestionID)).getGameName());
            } else {
                throw new SuggestionIDNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void viewAdminSuggestions(String username) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            for (String suggestion : Player.getPlayers().get(username).getSuggestions()) {
                System.out.println(Suggestion.getAllSuggestion().get(Integer.parseInt(suggestion)));
            }
        } else {
            throw new UsernameNotFoundException();
        }

    }

    public void viewPlatoStatistics(String username) {
        //TODO
    }

    public void gameHistory(String username) {
        //TODO
    }

    public void gameStatistics(String username, String gameName) {
        //TODO
    }

    public void addFriend(String username, String friendUserName) throws UsernameNotFoundException, ThisUserIsAlreadyYourFriendException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(friendUserName)) {
            if (!(Player.getPlayers().get(username).getFriends().containsKey(friendUserName))) {
                Player.getPlayers().get(username).getFriendRequest().put(friendUserName, Player.getPlayers().get(friendUserName));
            } else {
                throw new ThisUserIsAlreadyYourFriendException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void showFriends(String username) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(username).getFriends().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void removeFriend(String username, String usernameOfApplicant) throws UsernameNotFoundException, ThisUserIsNotYourFriendException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (Player.getPlayers().get(username).getFriends().containsKey(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriends().remove(usernameOfApplicant);
            } else {
                throw new ThisUserIsNotYourFriendException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void showFriendsRequests(String username) throws UsernameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(username).getFriendRequest().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void acceptRequests(String username, String friendUserName) throws UsernameNotFoundException, RequestNotFoundException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(friendUserName)) {
            if (Player.getPlayers().get(username).getFriendRequest().containsKey(friendUserName)) {
                Player.getPlayers().get(username).getFriendRequest().remove(friendUserName);
                Player.getPlayers().get(username).getFriends().put(friendUserName, Player.getPlayers().get(friendUserName));
                Player.getPlayers().get(friendUserName).getFriends().put(username, Player.getPlayers().get(username));
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void declineRequests(String username, String usernameOfApplicant) throws UsernameNotFoundException, RequestNotFoundException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (Player.getPlayers().get(username).getFriendRequest().containsKey(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriendRequest().remove(usernameOfApplicant);
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFoundException();
        }
    }

    public void viewFriendProfile(String username, String usernameOfIntendedUser) throws UsernameNotFoundException, ThisUserIsNotYourFriendException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfIntendedUser)) {
            if (Player.getPlayers().get(username).getFriends().containsKey(usernameOfIntendedUser)) {
                Player player = Player.getPlayers().get(usernameOfIntendedUser);
                System.out.println("Player name= " + player.getFirstName() + " " + player.getLastName());
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
}
