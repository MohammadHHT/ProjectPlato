package main.back.controller;

import main.back.account.Admin;
import main.back.account.Player;
import main.back.account.Suggestion;
import main.back.account.User;

import java.util.Map;

public interface UserCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "register":
                if (tokens.length == 3) {
                    return register(tokens[1], tokens[2]);
                } else {
                    return register(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                }
            case "login":
                return login(tokens[1], tokens[2]);
            case "allUsers":
                return allUsers(tokens[1], tokens[2]);
            case "sendSuggestion":
                return addSuggestion(tokens[1], tokens[2], tokens[3], tokens[4]);
            default:
                return "failed command";
        }
    }

    static String register(String field, String value) {
        switch (field) {
            case "username":
                return User.getUsers().containsKey(value) ? "false" : "true";
            case "phone":
                for (User u : User.getUsers().values()) {
                    if (u.getPhone().equalsIgnoreCase(value)) {
                        return "false";
                    }
                }
                return "true";
            case "email":
                for (User u : User.getUsers().values()) {
                    if (u.getEmail().equalsIgnoreCase(value)) {
                        return "false";
                    }
                }
                return "true";
            default:
                return "true";
        }
    }

    static String register(String firstName, String lastName, String username, String password, String email, String phone) {
        Player player = new Player(firstName, lastName, username, password, email, phone);
        player.login();
        return player.getToken();
    }

    static String login(String username, String password) {
        User user = User.getUsers().get(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (!user.isLogged()) {
                    user.login();
                    if (user instanceof Admin) {
                        return "admin " + user.getToken() + user.getFirstName() + " " + user.getLastName() + " " + user.getUsername() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth();
                    } else {
                        return "player " + user.getToken() + user.getFirstName() + " " + user.getLastName() + " " + user.getUsername() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth() + ((Player) user).getLevel() + " " + ((Player) user).getMoney();
                    }
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

    static String allUsers(String username, String token) {
        User user = User.getUsers().get(username);
        String usersInfo = null;
        if (user.getToken().equals(token)) {
            for (Map.Entry<String, User> entry : User.getUsers().entrySet()) {
                 usersInfo += entry.getValue().toString() + ("/");
            }
            return usersInfo;
//            return "amin lotfi aminlotfi 123456 amin@gmail.com 09304087303/mahdi hadi mahdihadiam 567890 mahdi@gmail.com 09126086363/mehran khaksar mehrankhaksar 654321 mehran@gmail.com 09122243286";
        } else {
            return null;
        }
    }

    static String addSuggestion(String username, String token, String playerUsername, String game) {
        User user = User.getUsers().get(username);
        Player player = Player.getPlayers().get(playerUsername);
        if (user.getToken().equals(token)) {
            new Suggestion(player, game);
        System.out.println(playerUsername + " added.");
            return "send successfully";
        } else {
            return "send suggestions unsuccessfully";
        }
    }
}
