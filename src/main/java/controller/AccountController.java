package controller;

import exception.*;
import model.*;

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
    }

    public String showPlatoStatistics(String username) {
        User user = User.getUsers().get(username);
        if (user instanceof Player) {
            Player player = (Player) user;
            return player.getLevel() + " " + player.getPlatoAge() + " " + calculateWins(player) + " " + player.getFriends().size();
        }
        return null;
    }

    private int calculateWins(Player player) {
        int wins = 0;
        for (String s : Game.getGamesName()) {
            if (player.getWins().containsKey(s)) {
                wins += player.getWins().get(s);
            }
        }
        return wins;
    }

    public String showHistory(String username) {
        User user = User.getUsers().get(username);
        if (user instanceof Player) {
            Player player = (Player) user;
            StringBuilder tmp = new StringBuilder();
            for (Long gl : player.getGameLogs()) {
                GameLog gameLog = GameLog.getGameLogs().get(gl);
                tmp.append(gameLog.getGame()).append(" ").append(gameLog.getDate()).append(" ");
            }
            return tmp.toString().trim();
        }
        return null;
    }

    public String showGameStatistics(String username, String game) throws GameNotFoundException {
        if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
            Player player = Player.getPlayers().get(username);
            String tmp = player.getLevel() + "\n";
            if (player.getPlays().get(game) != null) {
                tmp += player.getPlays().get(game) + " " + player.getWins().get(game) + " " + player.getDefeats().get(game);
            } else {
                tmp += 0;
            }
            return tmp.trim();
        } else {
            throw new GameNotFoundException();
        }
    }
}
