package view;

public class PlayerMenu extends Menu implements Back {
    private static final PlayerMenu playerMenu = new PlayerMenu();

    private PlayerMenu() {
    }

    public static PlayerMenu getPlayerMenu() {
        return playerMenu;
    }

    public void showPoint() {
        Client.getClient().send("User showPoint " + username);
    }

    public void showFavoriteGames() {
        Client.getClient().send("User showFavoriteGames " + username);
    }

    public void showAdminMessages() {
        Client.getClient().send("User showAdminMessages " + username);
    }

    public void showAdminSuggestions() {
        Client.getClient().send("User showAdminSuggestions " + username);
    }

    public void playSuggested() {
        System.out.print("Game Name: >");
        String game = scanner.nextLine();
        Client.getClient().send("User playSuggested " + username + " " + game);
    }

    public void showLastGame() {
        Client.getClient().send("User showLastGame " + username);
    }

    public void addFriend() {
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
                    break;
                case "showFavoriteGames":
                    break;
                case "showAdminMessages":
                    break;
                case "showAdminSuggestions":
                    break;
                case "playSuggested":
                    break;
                case "showLastGame":
                    break;
                case "addFriend":
                    break;
                case "accountMenu":
                    next(AccountMenu.getAccountMenu());
                    break;
                case "gameMenu":
                    next(GameMenu.getGameMenu());
                    break;
                default:
                    System.err.println("Incorrect command");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {

    }
}