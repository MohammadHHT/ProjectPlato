package main.back;

import controller.Server;
import main.back.account.Admin;

public class Main {
    public static void main(String[] args) {
        new Admin("bottt", "bottt", "bottt", "bottt", "bot@gamil.com", "989111234567");
        new Server().start();
    }
}
