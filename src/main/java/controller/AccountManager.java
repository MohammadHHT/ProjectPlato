package controller;

import exception.InvalidInputException;
import model.User;

public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();

    private AccountManager() {
    }

    private User loggedInUser = null;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void register(String firstName, String lastName, String username, String password, String email, String phoneNumber) throws InvalidInputException {
        if (Database.getUserByUsername(username) != null) {
            System.out.println("An account already exists with this username!");
        } else {
            try {
                Database.addAllUsers(new User(firstName, lastName, username, password, email, phoneNumber));
                System.out.println("Account created successfully");
            } catch (Exception e) {
                throw new InvalidInputException();
            }
        }
    }

    public void logIn(String username, String password) {
        if (loggedInUser != null) {
            System.out.println("You have already logged in!");
        } else {
            User user = Database.getUserByUsername(username);
            if (user == null) {
                System.out.println("No user with this info!");
            } else {
                if (!user.getPassword().contains(password)) {
                    System.out.println("Wrong password!");
                } else {
                    loggedInUser = user;
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
