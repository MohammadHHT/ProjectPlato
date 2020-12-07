package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User {
    private static HashMap<String, Admin> admins;

    public Admin(String firstName, String lastName, String username, String password, String email, String phoneNumber) {
        super(firstName, lastName, username, password, email, phoneNumber);
        admins.put(username, this);
    }

    public static void addAdmins(ArrayList<User> admins) {
        for (User a : admins) {
            Admin.admins.put(a.getUsername(), (Admin) a);
        }
    }

    public static HashMap<String, Admin> getAdmins() {
        return admins;
    }
}
