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
}
