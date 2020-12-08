package controller;

import exception.*;
import model.Admin;
import model.GameLog;
import model.Player;
import model.User;

public class AccountController {
    private static final AccountController accountController = new AccountController();

    private AccountController() {
    }

    public static AccountController getAccountController() {
        return accountController;
    }

    public String register(String firstName, String lastName, String username, String password, String email, String phoneNumber) throws UsernameIsAlreadyTaken {
        if (!User.getUsers().containsKey(username)) {
            if (User.getUsers().size() == 0) {
                new Admin(firstName, lastName, username, password, email, phoneNumber);
                return "Admin registered";
            } else {
                new Player(firstName, lastName, username, password, email, phoneNumber);
                return "Player registered";
            }
        } else {
            throw new UsernameIsAlreadyTaken();
        }
    }

    public String login(String username, String password) throws UsernameNotFound, IncorrectPassword {
        if (!User.getUsers().containsKey(username)) {
            throw new UsernameNotFound();
        } else if (!User.getUsers().get(username).getPassword().equals(password)) {
            throw new IncorrectPassword();
        } else {
            if (User.getUsers().get(username) instanceof Admin) {
                return "Admin logged in";
            } else {
                return "Player logged in";
            }
        }
    }

    public void delete(String username, String password) throws UsernameNotFound, IncorrectPassword {
        if (!User.getUsers().containsKey(username)) {
            throw new UsernameNotFound();
        } else if (!User.getUsers().get(username).getPassword().equals(password)) {
            throw new IncorrectPassword();
        } else {
            User user = User.getUsers().get(username);
            if (user instanceof Player) {
                Player.getPlayers().remove(username);
            } else {
                Admin.getAdmins().remove(username);
            }
            User.getUsers().remove(username);
        }
    }

    public String showInfo(String username) {
        User user = User.getUsers().get(username);
        return user.getFirstName() + " " + user.getLastName() + " "
                + user.getUsername() + " " + user.getEmail() + " " + user.getPhone();
    }

    public void changePassword(String username, String old, String next) throws IncorrectPassword {
        User user = User.getUsers().get(username);
        if (user.getPassword().equals(old)) {
            user.setPassword(next);
        } else {
            throw new IncorrectPassword();
        }
    }

    public void editField(String username, String field, String value) {
        User user = User.getUsers().get(username);
        switch (field) {
            case "username":
                user.setUsername(value);
                break;
            case "email":
                user.setEmail(value);
                break;
            case "phone":
                user.setPhone(value);
                break;
        }
        //TODO after changes happened, the new info must save in Database.
    }

    public String showStatistics(String username) {
        User user = User.getUsers().get(username);
        if (user instanceof Player) {
            Player player = (Player) user;
            String tmp = player.getLevel() + " " + player.getPlatoAge() + " ";
            int wins = 0;
            for (int i = 0; i < player.getGameLogs().size(); i++) {
                wins += GameLog.getGameLogs().get(player.getGameLogs().get(i)).getNumberOfWins();
            }
            tmp += wins + " " + player.getFriends().size();
            return tmp;
        }
        return null;
    }
}
