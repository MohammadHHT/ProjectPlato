package main.back.controller;

import main.back.account.Admin;
import main.back.account.Player;
import main.back.account.Suggestion;
import main.back.account.User;
import main.back.game.Event;

import java.time.LocalDateTime;

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
            case "search":
                return search(tokens[1], tokens[2], tokens[3]);
            case "suggestions":
                return suggestions(tokens[1], tokens[2]);
            case "events":
                return events(tokens[1], tokens[2]);
            case "addevent":
                return addEvent(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]));
            case "joinevent":
                return joinEvent(tokens[1], tokens[2], Long.parseLong(tokens[3]));
            case "users":
                return users(tokens[1], tokens[2]);
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
                        return "admin " + user.getUsername() + " " + user.getToken() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getUsername() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth();
                    } else {
                        return "player " + user.getUsername() + " " + user.getToken() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth() + " " + ((Player) user).getLevel() + " " + ((Player) user).getMoney();
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

    static String search(String username, String token, String input) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder users = new StringBuilder();
            for (String s : User.getUsers().keySet()) {
                if (s.contains(input)) {
                    users.append(s).append(" ");
                }
            }
            return users.toString().trim();
        }
        return null;
    }

    static String suggestions(String username, String token) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {
            String suggestions = "";
            for (Long l : player.getSuggestions()) {
                suggestions += Suggestion.getSuggestions().get(l).getGame();
            }
            return suggestions;
        }
        return null;
    }

    static String events(String username, String token) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder events = new StringBuilder();
            for (Event e : Event.getEvents().values()) {
                if (e.getEnd().isAfter(LocalDateTime.now())) {
                    events.append(e.toString()).append("/");
                }
            }
            return events.toString().substring(0, events.toString().length() - 1);
        }
        return null;
    }

    static String joinEvent(String username, String token, long eventID) {
        if (Player.getPlayers().get(username).getToken().equals(token)) {
            Event.getEvents().get(eventID).join(username);
        }
        return null;
    }

    static String addEvent(String username, String token, String game, int prize, int sday, int smonth, int syear, int fday, int fmonth, int fyear) {
        if (Admin.getAdmins().get(username).getToken().equals(token)) {
            Event event = new Event(game, LocalDateTime.of(syear, smonth, sday, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()), LocalDateTime.of(fyear, fmonth, fday, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()), prize);
            return String.valueOf(event.getEventID() + " " + event.getStart().getMonth() + " " + event.getEnd().getMonth());
        }
        return null;
    }

    static String users(String username, String token) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder users = new StringBuilder();
            for (String s : User.getUsers().keySet()) {
                users.append(s).append(" ");
            }
            System.out.println(users.toString().trim());
            return users.toString().trim();
        }
        return null;
    }
}
