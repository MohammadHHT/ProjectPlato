package view;

public class AccountMenu extends Menu implements Back {
    private static final AccountMenu accountMenu = new AccountMenu();

    private AccountMenu() {
    }

    public static AccountMenu getAccountMenu() {
        return accountMenu;
    }

    private void showInfo() {
        Client.getClient().send("User showInfo " + username);
    }

    private void changePassword() {
        String old, next;
        System.out.print("Old password: >");
        old = scanner.nextLine().trim();
        System.out.print("New password: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\w+")) {
            if (tmp.length() >= 6) {
                next = tmp;
            } else {
                System.err.println("Password must be at least 6 characters");
                return;
            }
        } else {
            System.err.println("Password includes alphanumeric characters only");
            return;
        }
        Client.getClient().send("User changePassword " + username + " " + old + " " + next);
    }

    private void editField() {
        String field = "", tmp, value = "";
        System.out.print("Field Name: >");
        tmp = scanner.nextLine().trim();
        switch (tmp) {
            case "username":
            case "email":
            case "phone":
                field = tmp;
                break;
        }
        if (field.length() > 0) {
            switch (field) {
                case "username":
                    value = getUsername();
                    break;
                case "email":
                    value = getEmail();
                    break;
                case "phone":
                    value = getPhone();
                    break;
            }
            if (value != null) {
                Client.getClient().send("User editField " + username + " " + field + " " + value);
                if (field.equals("email")) {
                    username = value;
                }
            } else {
                editField();
            }
        } else {
            System.err.println("There is no field with this label");
        }
    }

    private String getUsername() {
        System.out.print("New Username: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\w+")) {
            if (tmp.length() >= 3) {
                return tmp;
            } else {
                System.err.println("Username must be at least 3 characters");
                return null;
            }
        } else {
            System.err.println("Username includes alphanumeric characters only");
            return null;
        }
    }

    private String getEmail() {
        System.out.print("New Email: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("^\\w+@\\w+\\.(com|ir)$")) {
            return tmp;
        } else {
            System.err.println("Email is incorrect");
            return null;
        }
    }

    private String getPhone() {
        System.out.print("New Phone Number: >");
        String tmp = scanner.nextLine().trim();
        if (tmp.matches("\\+\\d+")) {
            if (tmp.length() == 13) {
                return tmp;
            } else {
                System.err.println("Phone Number must be exactly 12 numbers(plus +)");
                return null;
            }
        } else {
            System.err.println("Phone Number includes numbers only");
            return null;
        }
    }

    private void showPlatoStatistics() {
        Client.getClient().send("User showPlatoStatistics " + username);
    }

    private void showHistory() {
        Client.getClient().send("User showHistory " + username);
    }

    private void showGameStatistics() {
        System.out.print("Game Name: >");
        String game = scanner.nextLine();
        Client.getClient().send("User showGameStatistics " + username + " " + game);
    }

    private void logout() {
        username = null;
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine()) {
                case "showInfo":
                    showInfo();
                    break;
                case "changePassword":
                    changePassword();
                    break;
                case "editField":
                    editField();
                    break;
                case "showPlatoStatistics":
                    showPlatoStatistics();
                    break;
                case "showHistory":
                    showHistory();
                    break;
                case "showGameStatistics":
                    showGameStatistics();
                    break;
                case "logout":
                    logout();
                    next(LoginMenu.getLoginMenu());
                    return;
                case "back":
                    back();
                    return;
                default:
                    System.err.println("Incorrect command");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        pop();
        push(menu);
        menu.run();
    }
}
