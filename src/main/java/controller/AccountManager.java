package controller;

import exception.*;
import model.Admin;
import model.Player;
import model.User;

public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();

    private AccountManager() {
    }

    public static AccountManager getAccountManager() {
        return accountManager;
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

    public void deleteAccount(String username, String password) throws UsernameNotFound, IncorrectPassword {
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
                + user.getUsername() + " " + user.getEmail() + " " + user.getPhoneNumber();
    }

    public void changePassword(String username, String old, String next) throws IncorrectPassword {
        User user = User.getUsers().get(username);
        if (user.getPassword().equals(old)) {
            user.setPassword(next);
        } else {
            throw new IncorrectPassword();
        }
    }

    public void editField(String field, String value) {
        if (loggedInUser == null) {
            System.out.println("First log in your account...");
        } else {
            User user = loggedInUser;
            if (field.equalsIgnoreCase("first name")) {
                try {
                    user.setFirstName(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("last name")) {
                try {
                    user.setLastName(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("username")) {
                try {
                    user.setUsername(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("email")) {
                try {
                    user.setEmail(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("phone number")) {
                try {
                    user.setPhoneNumber(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("password")) {
                try {
                    user.setPassword(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("INVALID FIELD!");
            }
        }

        //TODO after changes happened, the new info must save in Database.
    }
}
