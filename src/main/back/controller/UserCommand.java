package controller;

import account.Player;
import account.User;

public interface UserCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "register":
                return register(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            case "login":
                return login(tokens[1], tokens[2]);
            default:
                return "failed command";
        }
    }

     static String register(String firstName, String lastName, String username, String password, String email, String phone) {
        if (!User.getUsers().containsKey(username)) {
            new Player(firstName, lastName, username, password, email, phone);
            return "done";
        } else {
            return "failed";
        }
    }

     static String login(String username, String password) {
        User user = User.getUsers().get(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (!user.isLogged()) {
                    return "done";
                } else {
                    return "failed logging";
                }
            } else {
                return "failed password";
            }
        } else {
            return "failed username";
        }
    }
}
