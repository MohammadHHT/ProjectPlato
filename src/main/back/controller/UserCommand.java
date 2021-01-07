package controller;

import account.Player;
import account.User;

import java.util.Arrays;

public interface UserCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "register":
                return register(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            break;
            case "login":
                return login(tokens[1], tokens[2]);
        }
    }

    private static String register(String firstName, String lastName, String username, String password, String email, String phone) {
        if (!User.getUsers().containsKey(username)) {
            new Player(firstName, lastName, username, password, email, phone);
            return "done";
        } else {
            return "failed";
        }
    }

    private static String login(String username, String password) {

    }
}
