package view;

import model.User;

public class AccountMenu extends Menu implements Back {
    private static final AccountMenu accountMenu = new AccountMenu();

    private AccountMenu() {
    }

    public static AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public void showInfo() {
        Client.getClient().send("User showInfo " + username);
    }

    public void changePassword() {
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

    public void editField(User user, String field, String content) {
    }

    public void showStatistics(User user) {
    }

    public void showHistory(User user) {
    }

    public void logout() {
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
                case "showStatistics":
                    showStatistics();
                    break;
                case "showHistory":
                    showHistory();
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
