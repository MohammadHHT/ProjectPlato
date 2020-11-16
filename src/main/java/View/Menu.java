package View;

import Model.User;
import java.util.ArrayList;

abstract class Menu {
    private static ArrayList<Menu> tree = new ArrayList<Menu>();
    private static User user;

    public static User getUser() { return user; }

    void stepBack() {}
}
