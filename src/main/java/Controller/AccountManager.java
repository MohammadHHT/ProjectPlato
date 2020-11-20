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
        //TODO
    }

    public void logOut() {
        //TODO
    }

    public void editPersonalInfo(String field, String newValue) {
        //TODO
    }

    public void viewPersonalInfo() {
        //TODO
    }

    public void deleteAccount(String username) {
        //TODO
    }
}
