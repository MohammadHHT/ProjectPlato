package main.back;

import main.back.account.Admin;
import main.back.account.Player;
import main.back.account.Suggestion;
import main.back.controller.Server;
import main.back.game.Event;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Player p = new Player("player", "player", "player", "player", "player@gamil.com", "989120000001");
        new Player("player", "player", "player1", "player1", "player1@gamil.com", "989120100001");
        new Player("player", "player", "player2", "player2", "player2@gamil.com", "989120200001");
        new Player("player", "player", "player3", "player3", "player3@gamil.com", "989120005001");
        new Player("player", "player", "player4", "player4", "player4@gamil.com", "989120000601");
        new Player("player", "player", "player5", "player5", "player1@gamil.com", "989120100001");
        new Player("player", "player", "player6", "player6", "player2@gamil.com", "989120200001");
        new Player("player", "player", "player7", "player7", "player3@gamil.com", "989120005001");
        new Player("player", "player", "player8", "player8", "player4@gamil.com", "989120000601");
        new Player("player", "player", "player9", "player9", "player1@gamil.com", "989120100001");
        new Player("player", "player", "player10", "player10", "player2@gamil.com", "989120200001");
        new Player("player", "player", "player11", "player11", "player3@gamil.com", "989120005001");
        new Player("player", "player", "player12", "player12", "player4@gamil.com", "989120000601");
        new Admin("admin", "admin", "admin", "admin", "admin@gamil.com", "989120000000");
        new Suggestion(p, "dots");
        new Suggestion(p, "battle");
        new Event("battle", LocalDateTime.now(), LocalDateTime.of(2022, 2, 21, 7, 28, 36), 1277);
        new Event("dots", LocalDateTime.now(), LocalDateTime.of(2021, 8, 25, 21, 56, 54), 56997);
        new Event("battle", LocalDateTime.now(), LocalDateTime.of(2022, 2, 21, 7, 28, 36), 1277);
        new Event("dots", LocalDateTime.now(), LocalDateTime.of(2021, 8, 25, 21, 56, 54), 56997);

        Player.getPlayers().get("player").addFriendRequest("player2");
        Player.getPlayers().get("player").acceptFriendRequest("player2");
        Player.getPlayers().get("player").addFriendRequest("player1");
        Player.getPlayers().get("player").acceptFriendRequest("player1");
        Player.getPlayers().get("player").addFriendRequest("player3");
        Player.getPlayers().get("player").acceptFriendRequest("player3");
        Player.getPlayers().get("player").addFriendRequest("player4");
        Player.getPlayers().get("player").acceptFriendRequest("player4");
        Player.getPlayers().get("player").addFriendRequest("player5");
        Player.getPlayers().get("player").acceptFriendRequest("player5");
        Player.getPlayers().get("player").addFriendRequest("player6");
        Player.getPlayers().get("player").acceptFriendRequest("player6");
        Player.getPlayers().get("player").addFriendRequest("player7");
        Player.getPlayers().get("player").acceptFriendRequest("player7");
        Player.getPlayers().get("player").addFriendRequest("player8");
        Player.getPlayers().get("player").acceptFriendRequest("player8");
        new Server().start();
    }
}

/*

 */