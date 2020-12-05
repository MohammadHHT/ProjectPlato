package view;

public class LoginMenu extends Menu {

    private int nextMenu;

    @Override
    public void run() {
        String[] commands;
        while (true) {
            String command = scanner.nextLine();
            commands = command.split("\\s");
            if (commands[0].equalsIgnoreCase("register")) {
                if (commands.length == 3) {
                    nextMenu = 0;
                    break;
                } else {
                    System.out.println("you should enter your username and password after the keyword < register >");
                }
            } else if (commands[0].equalsIgnoreCase("login")) {
                if (commands.length == 2) {
                    login(commands[1]);
                    if (Client.getClient().getResponse().equals("successfully logged in")) {
                        nextMenu = 1;
                        break;
                    }
                } else {
                    System.out.println("you should enter your username after the keyword < login >");
                }
            } else if (commands[0].equalsIgnoreCase("delete")) {
                if (commands.length == 2) {
                    delete(commands[1]);
                    if (Client.getClient().getResponse().equals("successfully deleted")) {
                        nextMenu = 2;
                        break;
                    }
                } else {
                    System.out.println("you should enter your username after the keyword < delete >");
                }
            } else {
                System.out.println("Wrong command");
            }
        }
        if (nextMenu == 0) {
            register(commands[1], commands[2]);
        } else
            next();
    }

    private void register(String username, String password) {
        RegisterMenu.getRegisterMenu().run();
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

    public void next() {
        if (nextMenu == 1) {
            PlayerPrimaryMenu.getPrimaryMenu().run();
        } else {
            RegisterMenu.getRegisterMenu().run();
        }
    }
}
