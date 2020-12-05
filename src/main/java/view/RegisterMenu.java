package view;

public class RegisterMenu extends Menu {
    private static final RegisterMenu registerMenu = new RegisterMenu();

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;

    private RegisterMenu() {
    }

    public static RegisterMenu getRegisterMenu() {
        return registerMenu;
    }

    @Override
    public void run() {
        while (true) {
            if (getFirstName()) {
                while (true) {
                    if (getLastName()) {
                        while (true) {
                            if (getUsername()) {
                                while (true) {
                                    if (getPassword()) {
                                        while (true) {
                                            if (getEmail()) {
                                                while (true) {
                                                    if (getPhone()) {
                                                        Client.getClient().send("User register " + firstName + " " + lastName + " " + username + " " + password + " " + email + " " + phone);
                                                        if (Client.getClient().getResponse().equals("successfully registered")) {
                                                            next();
                                                        }
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean getFirstName() {
        System.out.print("First Name: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("[a-zA-Z]+")) {
            firstName = tmp;
            return true;
        } else {
            System.err.println("First Name includes english letters only");
        }
        return false;
    }

    private boolean getLastName() {
        System.out.print("Last Name: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("[a-zA-Z]+")) {
            lastName = tmp;
            return true;
        } else {
            System.err.println("Last Name includes english letters only");
        }
        return false;
    }

    private boolean getUsername() {
        System.out.print("Username: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\w+")) {
            if (tmp.length() >= 3) {
                username = tmp;
                return true;
            } else {
                System.err.println("Username must be at least 3 characters");
            }
        } else {
            System.err.println("Username includes alphanumeric characters only");
        }
        return false;
    }

    private boolean getPassword() {
        System.out.print("Password: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\w+")) {
            if (tmp.length() >= 6) {
                password = tmp;
                return true;
            } else {
                System.err.println("Password must be at least 6 characters");
            }
        } else {
            System.err.println("Password includes alphanumeric characters only");
        }
        return false;
    }

    private boolean getEmail() {
        System.out.print("Email: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("^\\w+@\\w+\\.(com|ir)$")) {
            email = tmp;
            return true;
        } else {
            System.err.println("Email is incorrect");
        }
        return false;
    }

    private boolean getPhone() {
        System.out.print("Phone Number: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\+\\d+")) {
            if (tmp.length() == 13) {
                phone = tmp;
                return true;
            } else {
                System.err.println("Phone Number must be exactly 12 digits");
            }
        } else {
            System.err.println("Phone Number includes numbers only");
        }
        return false;
    }

    @Override
    public void next() {
        pop();
        PrimaryMenu.getPrimaryMenu().run();
    }
}
