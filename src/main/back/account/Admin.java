package main.back.account;

import main.back.account.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends User {

    private static HashMap<String, Admin> admins;

    static {
        admins = new HashMap<>();
    }

    public Admin(String firstName, String lastName, String username, String password, String email, String phone) {
        super(firstName, lastName, username, password, email, phone);
        admins.put(username, this);
    }

    public static void addAdmins(ArrayList<Admin> admins) {
        for (Admin a : admins) {
            Admin.admins.put(a.getUsername(), a);
        }
    }

    public static HashMap<String, Admin> getAdmins() {
        return admins;
    }
}
