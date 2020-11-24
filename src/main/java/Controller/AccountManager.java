package Controller;

import Model.User;

public class AccountManager {
    private static final AccountManager accountManager = new AccountManager();

    private AccountManager() {
    }

    private User loggedInUser = null;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void register(String firstName, String lastName, String username, String password, String email, String phone) {
        //TODO
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
        //TODO
    }

    public void viewPersonalInfo() {
        User user = loggedInUser;
        System.out.println(user.getFirstname() + " " + user.getLastname() + "\n"
                + user.getUsername() + " " + user.getEmail() + " " + user.getPhoneNumber());
    }

    public void deleteAccount(String username) {
        //TODO
    }
}
