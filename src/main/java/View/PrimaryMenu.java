package View;

import Model.User;

public class PrimaryMenu extends Menu {
    private static PrimaryMenu primaryMenu;

    private PrimaryMenu() {
    }

    public static PrimaryMenu getInstance() {
        if (primaryMenu == null) {
            return new PrimaryMenu();
        }
        return primaryMenu;
    }

    public void showPoint(User user) {}

    public void showFavoriteGames(User user) {}

    public void showBotMessages(User user) {}

    public void showBotSuggestions(User user) {}

    public void playSuggested(User user) {}

    public void showLastGame(User user) {}

    public void addFriend(User user, String friend) {}
}