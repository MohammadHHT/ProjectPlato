package View;

import java.util.Date;

public class BotMenu {
    private static BotMenu botMenu;

    private BotMenu() {
    }

    public static BotMenu getInstance() {
        if (botMenu == null) {
            return new BotMenu();
        }
        return botMenu;
    }

    public void addEvent(String game, Date start, Date finish, int score) {}

    public void showEvents() {}

    public void editEvent(String eventID, String field, String content) {}

    public void removeEvent(String eventID) {}

    public void suggest(String user, String game) {}

    public void showSuggestions() {}

    public void removeSuggestion(String suggestionID) {}

    public void showUsers() {}

    public void showUserProfile(String user) {}
}
