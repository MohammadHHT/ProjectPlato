package view;

public class PlayerMenu extends Menu implements Back {
    private static final PlayerMenu playerMenu = new PlayerMenu();

    private PlayerMenu() {
    }

    public static PlayerMenu getPlayerMenu() {
        return playerMenu;
    }

    private void showPoint() {
        Client.getClient().send("User showPoint " + username);
    }

    private void showFavoriteGames() {
        Client.getClient().send("User showFavoriteGames " + username);
    }

    private void showAdminMessages() {
        Client.getClient().send("User showAdminMessages " + username);
    }

    private void showAdminSuggestions() {
        Client.getClient().send("User showAdminSuggestions " + username);
    }

    private void playSuggested() {
        System.out.print("Game Name: >");
        String game = scanner.nextLine();
        Client.getClient().send("User playSuggested " + username + " " + game);
    }

    private void showLastGame() {
        Client.getClient().send("User showLastGame " + username);
    }

    private void addFriend() {
        System.out.print("Friend Username: >");
        String friend;
        String tmp = scanner.nextLine().trim();
        if (tmp.length() >= 3) {
            friend = tmp;
        } else {
            System.err.println("Invalid username");
            return;
        }
        Client.getClient().send("User addFriend " + username + " " + friend);
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine()) {
                case "showPoint":
                    showPoint();
                    break;
                case "showFavoriteGames":
                    showFavoriteGames();
                    break;
                case "showAdminMessages":
                    showAdminMessages();
                    break;
                case "showAdminSuggestions":
                    showAdminSuggestions();
                    break;
                case "playSuggested":
                    playSuggested();
                    break;
                case "showLastGame":
                    showLastGame();
                    break;
                case "addFriend":
                    addFriend();
                    break;
                case "accountMenu":
                    next(AccountMenu.getAccountMenu());
                    return;
                case "gameMenu":
                    next(GameMenu.getGameMenu());
                    return;
                default:
                    System.err.println("Incorrect command");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        push(menu);
        menu.run();
    }
}