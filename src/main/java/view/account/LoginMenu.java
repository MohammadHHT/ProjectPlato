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
        while (true) {
            String command = scanner.nextLine();
            commands = command.split("\\s");
            if (commands[0].equalsIgnoreCase("register")) {
                if (commands.length == 3) {
                    next(RegisterMenu.getRegisterMenu());
                    break;
                } else {
                    System.out.println("you should enter your username and password after the keyword < register >");
                }
            } else if (commands[0].equalsIgnoreCase("login")) {
                if (commands.length == 2) {
                    login(commands[1]);
                    if (Client.getClient().getResponse().equals("Admin logged in")) {
                        next(AdminMenu.getAdminMenu());
                        break;
                    } else if (Client.getClient().getResponse().equals("Player logged in")) {
                        next(PlayerMenu.getPlayerMenu());
                        break;
                    }
                } else {
                    System.out.println("you should enter your username after the keyword < login >");
                }
            } else if (commands[0].equalsIgnoreCase("delete")) {
                if (commands.length == 2) {
                    delete(commands[1]);
                    if (Client.getClient().getResponse().equals("Deleted")) {
                        next(RegisterMenu.getRegisterMenu());
                        break;
                    }
                } else {
                    System.out.println("you should enter your username after the keyword < delete >");
                }
            } else if (command.equalsIgnoreCase("back")) {
                back();
            } else {
                System.out.println("Wrong command");
            }
        }
    }

    private void delete(String username) {
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();
        Client.getClient().send("user delete " + username + " " + password);
    }

    private void login(String username) {
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();
        Client.getClient().send("user login " + username + " " + password);
    }

    @Override
    public void next(Menu menu) {
        menus.push(menu);
        menu.run();
    }
}
