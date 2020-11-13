package Model;

import java.util.ArrayList;

public class User {
    private String firstname;
    private String lastname;
    private String username;
    private long userID;
    private String password;
    private String email;
    private String phoneNumber;
    private static ArrayList<User> users;

    static {
        users = new ArrayList<User>();
    }

    public User(String firstname, String lastname, String username, long userID, String password, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void daysGone() {
        //TODO
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public long getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
