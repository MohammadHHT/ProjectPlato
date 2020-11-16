package Controller;


import Model.User;

public class AccountManager {
    private User loggedInUser = null;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void register(String type, String firstName, String lastName, String userName, String password, String email, String phoneNumber) {
        //TODO
    }

    public void logIn(String userName, String password) {
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

    public void deleteAccount(String userName) {
        //TODO
    }
}
