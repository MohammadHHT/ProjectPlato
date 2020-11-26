package view;

import java.util.Date;

public class BotMenu extends Menu {
    private static final BotMenu botMenu = new BotMenu();

    private BotMenu() {
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
