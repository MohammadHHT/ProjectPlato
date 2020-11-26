package view;

import model.User;

public class PrimaryMenu extends Menu {
    private static final PrimaryMenu primaryMenu = new PrimaryMenu();

    private PrimaryMenu() {
    }

    public static PrimaryMenu getPrimaryMenu() {
        return primaryMenu;
    }

    public void showPoint(User user) {}

    public void showFavoriteGames(User user) {}

    public void showBotMessages(User user) {}

    public void showBotSuggestions(User user) {}

    public void playSuggested(User user) {}

    public void showLastGame(User user) {}

    public void addFriend(User user, String friend) {}

    @Override
    public void run() {

    }
}