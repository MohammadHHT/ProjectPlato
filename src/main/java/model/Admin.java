package model;

public class Admin extends User {

    private static Admin admin;
    public Admin(String firstname, String lastname, String username, String password, String email, String phoneNumber) {
        super(firstname, lastname, username, password, email, phoneNumber);
        admin = this;
    }

    public static Admin getAdmin() {
        return admin;
    }
}
