package main.back;

import main.back.account.Admin;
import main.back.controller.Server;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin", "admin", "admin", "admin@gamil.com", "989198178163");
        admin.setLogged(false);
        new Server().start();
    }
}
