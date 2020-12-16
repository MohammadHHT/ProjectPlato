package controller;

import exception.RequestNotFound;
import exception.UsernameNotFound;
import model.Player;

public class FriendController {
    private static final FriendController friendController = new FriendController();

    private FriendController() {
    }

    public static FriendController getFriendController() {
        return friendController;
    }

    public String showFriends(String username) {
        StringBuilder tmp = new StringBuilder();
        Player player = Player.getPlayers().get(username);
        int i = 1;
        for (String s : player.getFriends()) {
            tmp.append(i++).append('.').append(s).append('\n');
        }
        return tmp.toString().trim();
    }

    public void removeFriend(String username, String friend) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(friend)) {
            Player.getPlayers().get(username).getFriends().remove(friend);
        } else {
            throw new UsernameNotFound();
        }
    }

    public String showRequests(String username) {
        StringBuilder tmp = new StringBuilder();
        int i = 1;
        for (String s : Player.getPlayers().get(username).getFriendRequest()) {
            tmp.append(i++).append('.').append(s).append('\n');
        }
        return tmp.toString().trim();
    }

    public void accept(String username, String friend) throws UsernameNotFound, RequestNotFound {
        Player player = Player.getPlayers().get(username);
        if (Player.getPlayers().get(friend) != null) {
            if (player.getFriendRequest().contains(friend)) {
                player.getFriends().add(friend);
                player.getFriendRequest().remove(friend);
            } else {
                throw new RequestNotFound();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public void decline(String username, String friend) throws UsernameNotFound, RequestNotFound {
        Player player = Player.getPlayers().get(username);
        if (Player.getPlayers().get(friend) != null) {
            if (player.getFriendRequest().contains(friend)) {
                player.getFriendRequest().remove(friend);
            } else {
                throw new RequestNotFound();
            }
        } else {
            throw new UsernameNotFound();
        }
    }
}
