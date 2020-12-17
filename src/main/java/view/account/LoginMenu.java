package view.account;

import view.Back;
import view.Client;
import view.Menu;
import view.primary.AdminMenu;
import view.primary.PlayerMenu;

public class LoginMenu extends Menu implements Back {
    private static final LoginMenu loginMenu = new LoginMenu();

    private LoginMenu() {
    }

    public static LoginMenu getLoginMenu() {
        return loginMenu;
    }

    @Override
    public void run() {
        String[] commands;
        label:
        while (true) {
            String command = scanner.nextLine();
            commands = command.split("\\s");
            switch (commands[0]) {
                case "register":
                    if (commands.length == 3) {
                        next(RegisterMenu.getRegisterMenu());
                        break label;
                    } else {
                        System.out.println("you should enter your username and password after the keyword < register >");
                    }
                    break;
                case "login":
                    if (login(commands))
                        break label;
                    break;
                case "delete":
                    if (commands.length == 2) {
                        delete(commands[1]);
                        if (Client.getClient().getResponse().equals("Deleted")) {
                            next(RegisterMenu.getRegisterMenu());
                            break label;
                        }
                    } else {
                        System.out.println("you should enter your username after the keyword < delete >");
                    }
                    break;
                case "back":
                    back();
                    break;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }

    }

    private void delete(String username) {
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();
        Client.getClient().send("user delete " + username + " " + password);
    }

    private boolean login(String[] commands) {
        if (commands.length == 2) {
            System.out.println("Enter your password : ");
            String password = scanner.nextLine();
            Client.getClient().send("user login " + commands[1] + " " + password);
            if (Client.getClient().getResponse().equals("Admin logged in")) {
                next(AdminMenu.getAdminMenu());
                return true;
            } else if (Client.getClient().getResponse().equals("Player logged in")) {
                next(PlayerMenu.getPlayerMenu());
                return true;
            } else return false;
        } else {
            System.out.println("you should enter your username after the keyword < login >");
            return false;
        }
    }

    @Override
    public void next(Menu menu) {
        menus.push(menu);
        menu.run();
    }
}
