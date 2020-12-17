package controller;

import exception.*;
import exception.game.GameNotFound;
import model.*;
import view.Menu;

public class AccountController {
    private static final AccountController accountController = new AccountController();

    private AccountController() {
    }

    public static AccountController getAccountController() {
        return accountController;
    }

    public String register(String firstName, String lastName, String username, String password, String email, String phone) throws UsernameIsAlreadyTaken {
        if (!User.getUsers().containsKey(username)) {
            Menu.username = username;
            if (User.getUsers().size() == 0) {
                new Admin(firstName, lastName, username, password, email, phone);
                return "admin";
            } else {
                new Player(firstName, lastName, username, password, email, phone);
                return "player";
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
            Menu.username = username;
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
        return User.getUsers().get(username).toString();
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
            case "firstname":
                user.setFirstName(value);
                break;
            case "lastname":
                user.setLastName(value);
                break;
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
        return Player.getPlayers().get(username).toString();
    }

    public String showHistory(String username) {
        Player player = Player.getPlayers().get(username);
        StringBuilder tmp = new StringBuilder();
        for (Long l : player.getGameLogs()) {
            GameLog gameLog = GameLog.getGameLogs().get(l);
            tmp.append(gameLog.getGame()).append(": ").append(gameLog.getDate().getYear()).append('-').append(gameLog.getDate().getMonth()).append('-').append(gameLog.getDate().getDayOfMonth()).append('\n');
        }
        return tmp.toString().trim();
    }

    public String showGameStatistics(String username, String game) throws GameNotFound {
        if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
            Player player = Player.getPlayers().get(username);
            if (player.getPlays().get(game) != null) {
                return "Played: " + player.getPlays().get(game) + "\nWon: " + player.getWins().get(game) + "\nDefeated" + player.getDefeats().get(game);
            } else {
                return "";
            }
        } else {
            throw new GameNotFound();
        }
    }
}
