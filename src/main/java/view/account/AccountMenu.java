package view.account;

import view.Back;
import view.Client;
import view.Menu;

public class AccountMenu extends Menu implements Back {
    private static final AccountMenu accountMenu = new AccountMenu();

    private AccountMenu() {
    }

    public static AccountMenu getAccountMenu() {
        return accountMenu;
    }

    private void showInfo() {
        Client.getClient().send("user showInfo " + username);
        System.out.println(Client.getClient().getResponse());
    }

    private void changePassword() {
        String old, next;
        System.out.print("Old password: >");
        old = scanner.nextLine().trim();
        System.out.print("New password: >");
        next = scanner.nextLine().trim();
        if ((old.matches("\\w+") && old.length() >= 6) && (next.matches("\\w+") && next.length() >= 6)) {
            Client.getClient().send("user changePassword " + username + " " + old + " " + next);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Invalid password!");
        }
    }

    private void editField() {
        String field, value;
        System.out.print("Field Name (first name • last name • username • email • phone): >");
        field = scanner.nextLine().replaceAll(" ", "");
        switch (field) {
            case "firstname":
                value = getFirstName();
                break;
            case "lastname":
                value = getLastName();
                break;
            case "username":
                value = getUsername();
                break;
            case "email":
                value = getEmail();
                break;
            case "phone":
                value = getPhone();
                break;
            default:
                value = null;
                break;
        }
        if (value != null) {
            if (field.equals("username")) {
                username = value;
            } else {
                Client.getClient().send("user editField " + username + " " + field + " " + value);
            }
        } else {
            System.out.println("Wrong input!");
        }
    }

    private String getFirstName() {
        System.out.print("First Name: >");
        String firstName = scanner.nextLine().trim();
        if (firstName.matches("[a-zA-Z]+")) {
            return firstName;
        } else {
            System.out.println("Invalid first name");
            return null;
        }
    }

    private String getLastName() {
        System.out.print("First Name: >");
        String lastName = scanner.nextLine().trim();
        if (lastName.matches("[a-zA-Z]+")) {
            return lastName;
        } else {
            System.out.println("Invalid last name");
            return null;
        }
    }

    private String getUsername() {
        System.out.print("New Username: >");
        String username = scanner.nextLine().trim();
        if (username.matches("\\w+") && username.length() >= 3) {
            return username;
        } else {
            System.out.println("Invalid username!");
            return null;
        }
    }

    private String getEmail() {
        System.out.print("New Email: >");
        String email = scanner.nextLine().trim();
        if (email.matches("^\\w+@\\w+\\.(com|ir)$")) {
            return email;
        } else {
            System.out.println("Invalid email!");
            return null;
        }
    }

    private String getPhone() {
        System.out.print("New Phone Number: >");
        String phone = scanner.nextLine().trim();
        if (phone.matches("\\+\\d+") && phone.length() == 13) {
            return phone;
        } else {
            System.out.println("Invalid phone number!");
            return null;
        }
    }

    private void showPlatoStatistics() {
        if (rank == Rank.PLAYER) {
            Client.getClient().send("user showPlatoStatistics " + username);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Just players!");
        }
    }

    private void showHistory() {
        if (rank == Rank.PLAYER) {
            Client.getClient().send("user showHistory " + username);
            System.out.println(Client.getClient().getResponse());
        } else {
            System.out.println("Just players!");
        }
    }

    private void showGameStatistics() {
        if (rank == Rank.PLAYER) {
            System.out.print("Game Name (Battle Sea • Dots And Boxes): >");
            String game = scanner.nextLine().replaceAll(" ", "");
            Client.getClient().send("user showGameStatistics " + username + " " + game);

            if (Client.getClient().getResponse().length() > 0) {
                System.out.println(Client.getClient().getResponse());
            } else {
                System.out.println("You've not played the game yet!");
            }
        } else {
            System.out.println("Just players!");
        }
    }

    private void logout() {
        username = null;
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "show info":
                    showInfo();
                    break;
                case "change password":
                    changePassword();
                    break;
                case "edit field":
                    editField();
                    break;
                case "show plato statistics":
                    showPlatoStatistics();
                    break;
                case "show history":
                    showHistory();
                    break;
                case "show game statistics":
                    showGameStatistics();
                    break;
                case "logout":
                    logout();
                    next(LoginMenu.getLoginMenu());
                    return;
                case "friends menu":
                    if (rank == Rank.PLAYER) {
                        next(FriendMenu.getFriendMenu());
                    } else {
                        System.out.println("Just players!");
                    }
                    break;
                case "back":
                    back();
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        menus.push(menu);
        menu.run();
    }
}
