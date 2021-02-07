package main.back.account;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

public class User {

    private static HashMap<String, User> users;

    private String token;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime date;
    private boolean logged;

    static {
        users = new HashMap<>();
    }

    public User(String firstName, String lastName, String username, String password, String email, String phone) {
        token = null;
        users.put(username, this);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        date = LocalDateTime.now();
    }

    private static String generateToken() {
        byte[] randomBytes = new byte[24];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }

    public static void addUsers(ArrayList<User> users) {
        for (User u : users) {
            User.users.put(u.username, u);
        }
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public String getToken() {
        return token;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void login() {
        logged = true;
        token = generateToken();
    }

    public void logout() {
        logged = false;
        token = null;
    }

    public boolean isLogged() {
        return logged;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + username + " " + password + " " + email + " " + phone;
    }
}