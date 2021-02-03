package main.back;

import main.back.account.Admin;
import main.back.controller.Server;
import main.back.game.SeaBattle.Grid;

public class Main {
    public static void main(String[] args) {
        new Admin("admin", "admin", "admin", "admin", "admin@gamil.com", "989120000000");
        new Server().start();
    }
}