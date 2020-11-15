package View;

import Model.User;
import javafx.scene.image.Image;

public class AccountMenu extends Menu {
    private static AccountMenu accountMenu;

    private AccountMenu() {
    }

    public static AccountMenu getInstance() {
        if (accountMenu == null) {
            return new AccountMenu();
        }
        return accountMenu;
    }

    public void viewInfo(User user) {
    }

    public boolean changePassword(User user, String before, String after) {
        return true;
    }

    public boolean editField(User user, String field, String content) {
        return true;
    }

    public boolean editField(User user, Image avatar) {
        return true;
    }

    public void getStatistic(User user) {
    }

    public void getStatistic(User user, String game) {
    }

    public void getHistory(User user) {
    }

    public void logout() {
    }
}
