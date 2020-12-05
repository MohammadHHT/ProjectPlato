package controller;

import exception.*;
import model.Admin;
import model.Player;
import model.User;

import javax.jws.soap.SOAPBinding;

public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();

    private User loggedInUser = null;

    private AccountManager() {
    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void register(String firstName, String lastName, String username, String password, String email, String phoneNumber) throws UsernameIsAlreadyTakenException {
        if (User.getUsers().containsKey(username)) {
            throw new UsernameIsAlreadyTakenException();
        } else {
            if (User.getUsers().size() == 0) {
                loggedInUser = new Admin(firstName, lastName, username, password, email, phoneNumber);
            } else {
                loggedInUser = new Player(firstName, lastName, username, password, email, phoneNumber);
            }
        }
    }

    public void login(String username, String password) throws AlreadyLoggedIn, UsernameNotFoundException, IncorrectPasswordException {
        if (loggedInUser != null) {
            throw new AlreadyLoggedIn();
        } else if (!User.getUsers().containsKey(username)) {
            throw new UsernameNotFoundException();
        } else if (!User.getUsers().get(username).getPassword().equals(password)) {
            throw new IncorrectPasswordException();
        } else {
            loggedInUser = User.getUsers().get(username);
        }
    }

    public void logout() {
        loggedInUser = null;
    }

    public void deleteAccount(String username, String password) throws UsernameNotFoundException, IncorrectPasswordException {
        if (!User.getUsers().containsKey(username)) {
            throw new UsernameNotFoundException();
        } else if (!User.getUsers().get(username).getPassword().equals(password)) {
            throw new IncorrectPasswordException();
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

    public void editPersonalInfo(String field, String newValue) {
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

    public void viewPersonalInfo() {
        User user = loggedInUser;
        System.out.println(user.getFirstName() + " " + user.getLastName() + "\n"
                + user.getUsername() + " " + user.getEmail() + " " + user.getPhoneNumber());
    }
}
