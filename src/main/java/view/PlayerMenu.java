package view;

public class PlayerMenu extends Menu {
    private static final PlayerMenu playerMenu = new PlayerMenu();

    private PlayerMenu() {
    }

    public static PlayerMenu getPlayerMenu() {
        return playerMenu;
    }

    private void showPoint() {
        Client.getClient().send("user showPoint " + username);
        System.out.println("Score: " + Client.getClient().getResponse());
    }

    private void showFavoriteGames() {
        Client.getClient().send("user showFavoriteGames " + username);

        String[] tokens = Client.getClient().getResponse().split("\\n");
        if (tokens.length > 0) {
            System.out.println("Favorites:\n" + Client.getClient().getResponse());
        } else {
            System.out.println("Empty!");
        }
    }

    private void showAdminMessages() {
        Client.getClient().send("user showAdminMessages " + username);

        String[] tokens = Client.getClient().getResponse().split("\\n");
        if (tokens.length > 0) {
            System.out.println("Messages:\n" + Client.getClient().getResponse());
        } else {
            System.out.println("Empty!");
        }
    }

    private void showAdminSuggestions() {
        Client.getClient().send("user showAdminSuggestions " + username);

        String[] tokens = Client.getClient().getResponse().split("\\n");
        if (tokens.length > 0) {
            System.out.println("Suggestions:\n" + Client.getClient().getResponse());
        } else {
            System.out.println("Empty!");
        }
    }

    private void playSuggested() {
        System.out.print("Game Name: >");
        String game = scanner.nextLine();
        if (game.length() > 0) {
            Client.getClient().send("user playSuggested " + username + " " + game);
        } else {
            System.out.println("Game name can not be empty!");
        }

        //TODO
    }

    private void showLastGame() {
        Client.getClient().send("user showLastGame " + username);

        if (Client.getClient().getResponse().length() > 0) {
            System.out.println("Name: " + Client.getClient().getResponse());
        } else {
            System.out.println("You've not played yet!");
        }
    }

    private void addFriend() {
        System.out.print("Friend Username: >");
        String friend = scanner.nextLine().trim();
        if (friend.matches("\\w+") && friend.length() >= 3) {
            Client.getClient().send("user addFriend " + username + " " + friend);
        } else {
            System.out.println("Invalid username!");
        }

        System.out.println(Client.getClient().getResponse());
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine()) {
                case "show point":
                    showPoint();
                    break;
                case "show favorite games":
                    showFavoriteGames();
                    break;
                case "show admin messages":
                    showAdminMessages();
                    break;
                case "show admin suggestions":
                    showAdminSuggestions();
                    break;
                case "play suggested":
                    playSuggested();
                    //TODO
                    break;
                case "show last game":
                    showLastGame();
                    break;
                case "add friend":
                    addFriend();
                    break;
                case "account menu":
                    next(AccountMenu.getAccountMenu());
                    return;
                case "game menu":
                    next(GameMenu.getGameMenu());
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