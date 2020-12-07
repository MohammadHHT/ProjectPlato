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
        StringBuilder tmp = new StringBuilder();
        for (String inbox : Player.getPlayers().get(username).getInbox()) {
            tmp.append(inbox).append(" ");
        }
        return tmp.toString().trim();
    }

    public String showAdminSuggestions(String username) {
        ArrayList<Long> suggestions = Player.getPlayers().get(username).getSuggestions();
        String tmp = "";
        for (Long s : suggestions) {
            tmp += Suggestion.getSuggestions().get(s).getGameName() + " ";
        }
        return tmp.trim();
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
        Player player = Player.getPlayers().get(friend);
        if (player != null) {
            if (!Player.getPlayers().get(username).getFriends().containsKey(friend)) {
                player.addFriendRequest(username);
            } else {
                throw new AlreadyYourFriend();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void addFavoriteGame(String username, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().containsKey(Integer.parseInt(gameName))) {
                Player.getPlayers().get(username).getFavoriteGames().add(Game.getGames().get(Integer.parseInt(gameName)));
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void deleteFavoriteGame(String username, String gameName) throws UsernameNotFound, GameNotFoundException {
        if (Player.getPlayers().containsKey(username)) {
            if (Game.getGames().containsKey(Integer.parseInt(gameName))) {
                Player.getPlayers().get(username).getFavoriteGames().remove(Game.getGames().get(Integer.parseInt(gameName)));
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
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

    public void viewPlatoStatistics(String username) {
        //TODO
    }

    public void gameHistory(String username) {
        //TODO
    }

    public void gameStatistics(String username, String gameName) {
        //TODO
    }

    public void showFriends(String username) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(username)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(username).getFriends().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void removeFriend(String username, String usernameOfApplicant) throws UsernameNotFound, ThisUserIsNotYourFriendException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (Player.getPlayers().get(username).getFriends().containsKey(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriends().remove(usernameOfApplicant);
            } else {
                throw new ThisUserIsNotYourFriendException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void showFriendsRequests(String username) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(username)) {
            for (Map.Entry<String, Player> entry : Player.getPlayers().get(username).getFriendRequest().entrySet()) {
                System.out.println(entry.getValue().getUsername());
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void acceptRequests(String username, String friendUserName) throws UsernameNotFound, RequestNotFoundException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(friendUserName)) {
            if (Player.getPlayers().get(username).getFriendRequest().containsKey(friendUserName)) {
                Player.getPlayers().get(username).getFriendRequest().remove(friendUserName);
                Player.getPlayers().get(username).getFriends().put(friendUserName, Player.getPlayers().get(friendUserName));
                Player.getPlayers().get(friendUserName).getFriends().put(username, Player.getPlayers().get(username));
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void declineRequests(String username, String usernameOfApplicant) throws UsernameNotFound, RequestNotFoundException {
        if (Player.getPlayers().containsKey(username) && Player.getPlayers().containsKey(usernameOfApplicant)) {
            if (Player.getPlayers().get(username).getFriendRequest().containsKey(usernameOfApplicant)) {
                Player.getPlayers().get(username).getFriendRequest().remove(usernameOfApplicant);
            } else {
                throw new RequestNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void viewFriendProfile(String username, String usernameOfIntendedUser) throws UsernameNotFound, ThisUserIsNotYourFriendException {
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
            throw new UsernameNotFound();
        }
    }
}
