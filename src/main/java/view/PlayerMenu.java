package view;

public class PlayerMenu extends Menu {
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
        Client.getClient().send("User playSuggested " + username);
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

    }

    @Override
    public void next() {

    }
}