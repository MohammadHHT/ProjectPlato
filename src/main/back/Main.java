package main.back;

import main.back.account.Admin;
import main.back.account.Player;
import main.back.controller.Server;
import main.back.game.SeaBattle.Grid;

public class Main {
    public static void main(String[] args) {
        new Player("player", "player", "player", "player", "player@gamil.com", "989120000001");
        new Player("player", "player", "player1", "player1", "player1@gamil.com", "989120100001");
        new Player("player", "player", "player2", "player2", "player2@gamil.com", "989120200001");
        new Player("player", "player", "player3", "player3", "player3@gamil.com", "989120005001");
        new Player("player", "player", "player4", "player4", "player4@gamil.com", "989120000601");
        new Admin("admin", "admin", "admin", "admin", "admin@gamil.com", "989120000000");
        new Server().start();
    }
}