package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class User {
    private static HashMap<String, User> users;

    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    static {
        users = new HashMap<>();
    }

    public User(String firstName, String lastName, String username, String password, String email, String phoneNumber) {
        users.put(username, this);
        userID = IDGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    private int IDGenerator() {
        Random random = new Random();
        return (username.charAt(0) % 100) + ((username.charAt(1) % 100) * 100) + ((username.charAt(2) % 100) * 10000) + (random.nextInt(10000) * 1000000);
    }

    public void daysGone() {
        //TODO
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
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

    public static HashMap<String, User> getUsers() {
        return users;
    }
}
