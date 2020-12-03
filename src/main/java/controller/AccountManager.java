package controller;

import exception.InvalidInputException;
import exception.UsernameIsAlreadyTakenException;
import model.Admin;
import model.Player;
import model.User;

public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();
    static boolean hasExistAdmin = false;

    private AccountManager() {
    }

    private Admin loggedInAdmin = null;
    private Player loggedInPlayer = null;

    public Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public void register(String firstName, String lastName, String username, String password, String email, String phoneNumber) throws
            InvalidInputException, UsernameIsAlreadyTakenException {
        if (!hasExistAdmin) {
            System.out.println("Please create a admin account to start.");
            try {
                new Admin(firstName, lastName, username, password, email, phoneNumber);
                hasExistAdmin = true;
            } catch (Exception e) {
                throw new InvalidInputException();
            }
        }
        if (Player.getPlayers().containsKey(username)) {
            throw new UsernameIsAlreadyTakenException();
        } else {
            try {
                Player.getPlayers().put(username, new Player(firstName, lastName, username, password, email, phoneNumber));
                System.out.println("Account created successfully");
            } catch (Exception e) {
                throw new InvalidInputException();
            }
        }
    }

    public void logIn(String username, String password) {
        if (loggedInAdmin != null || loggedInPlayer != null) {
            System.out.println("You have already logged in!");
        } else {
            Admin admin = Admin.getAdmin();
            Player player = Player.getPlayers().get(username);
            if (player == null) {
                System.out.println("No user with this info!");
            } else {
                if (!admin.getPassword().equals(password) || !player.getPassword().equals(password)) {
                    System.out.println("Wrong password!");
                } else {
                    System.out.println("Welcome " + user.getUsername());
                }
            }
        }
    }

    public void logOut() {
        if (loggedInUser == null) {
            System.out.println("You haven't already logged in!");
        } else {
            loggedInUser = null;
            System.out.println("You logged out successfully!");
        }
    }

    public void editPersonalInfo(String field, String newValue) {
        if (loggedInUser == null) {
            System.out.println("First log in your account...");
        } else {
            User user = loggedInUser;
            if (field.equalsIgnoreCase("first name")) {
                try {
                    user.setFirstname(newValue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (field.equalsIgnoreCase("last name")) {
                try {
                    user.setLastname(newValue);
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
        System.out.println(user.getFirstname() + " " + user.getLastname() + "\n"
                + user.getUsername() + " " + user.getEmail() + " " + user.getPhoneNumber());
    }

    public void deleteAccount(String username, String password) {
        User user = Database.getUserByUsername(username);
        if (user == null) {
            System.out.println("No user with this info!");
        } else {
            if (!user.getPassword().contains(password)) {
                System.out.println("Wrong password!");
            } else {
                Database.allUsers.remove(user);
                System.out.println("Your account has removed successfully!");
            }
        }
    }
}
