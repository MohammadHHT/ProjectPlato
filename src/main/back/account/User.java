package account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class User {

    private static HashMap<String, User> users;

    private long userID;
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
        userID = IDGenerator();
        users.put(username, this);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        date = LocalDateTime.now();
        logged = false;
    }

    private long IDGenerator() {
        Random random = new Random();
        return 1000000;
    }

    public static void addUsers(ArrayList<User> users) {
        for (User u : users) {
            User.users.put(u.username, u);
        }
    }

    public static HashMap<String, User> getUsers() {
        return users;
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

    public boolean isLogged() {
        return logged;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + username + " " + password + " " + email + " " + phone;
    }
}