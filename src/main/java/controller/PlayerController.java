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

    public void playSuggested(String username, String game) throws GameNotFoundException {
        if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
            //TODO
        } else {
            throw new GameNotFoundException();
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

    public void addFavoriteGame(String username, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().contains(gameName)) {
                Player.getPlayers().get(username).getFavoriteGames().add(gameName);
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void deleteFavoriteGame(String username, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().contains(gameName)) {
                Player.getPlayers().get(username).getFavoriteGames().remove(gameName);
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void joinEvent(String username, String eventID, String gameName) throws EventIDNotFound {
        if (Event.getEvents().containsKey(Integer.parseInt(eventID))) {
            GameController.runGameForEvent(username, Event.getEvents().get(Integer.parseInt(eventID)).getScore(),
                    gameName);
        } else {
            throw new EventIDNotFound();
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

    public String showFriends(String username) throws UsernameNotFound {
        ArrayList<String> friends = Player.getPlayers().get(username).getFriends();
        String tmp = "";
        if (Player.getPlayers().containsKey(username)) {
            for (String friend : friends) {
                tmp += Player.getPlayers().get(friend).getUsername() + " ";
            }
        } else {
            throw new UsernameNotFound();
        }
        return tmp.trim();
    }

    public void removeFriend(String username, String usernameOfApplicant) throws UsernameNotFound, ThisUserIsNotYourFriendException {
        ArrayList<String> friends = Player.getPlayers().get(username).getFriends();
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (friends.contains(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriends().remove(usernameOfApplicant);
            } else {
                throw new ThisUserIsNotYourFriendException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public String showFriendsRequests(String username) throws UsernameNotFound {
        ArrayList<String> friendRequest = Player.getPlayers().get(username).getFriendRequest();
        String tmp = "";
        if (Player.getPlayers().containsKey(username)) {
            for (String friend : friendRequest) {
                tmp += Player.getPlayers().get(friend).getUsername();
            }
        } else {
            throw new UsernameNotFound();
        }
        return tmp.trim();
    }

    public void acceptRequests(String username, String friendUserName) throws UsernameNotFound, RequestNotFoundException {
        ArrayList<String> friendRequest = Player.getPlayers().get(username).getFriendRequest();
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(friendUserName)) {
            if (friendRequest.contains(friendUserName)) {
                Player.getPlayers().get(username).getFriendRequest().remove(friendUserName);
                Player.getPlayers().get(username).getFriends().add(friendUserName);
                Player.getPlayers().get(friendUserName).getFriends().add(username);
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void declineRequests(String username, String usernameOfApplicant) throws UsernameNotFound, RequestNotFoundException {
        ArrayList<String> friendRequest = Player.getPlayers().get(username).getFriendRequest();
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (friendRequest.contains(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriendRequest().remove(usernameOfApplicant);
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void viewFriendProfile(String username, String usernameOfIntendedUser) throws UsernameNotFound, ThisUserIsNotYourFriendException {
        ArrayList<String> friends = Player.getPlayers().get(username).getFriends();
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfIntendedUser)) {
            if (friends.contains(usernameOfIntendedUser)) {
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
            throw new UsernameNotFound();
        }
    }
}
