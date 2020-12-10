package view;

import java.util.Scanner;
import java.util.Stack;

public abstract class Menu {
    enum Rank {
        ADMIN, PLAYER
    }

    protected static Stack<Menu> menus = new Stack<>();
    protected static Scanner scanner = new Scanner(System.in);
    protected static String username = null;
    protected static Rank rank = null;

    public abstract void run();

    public abstract void next(Menu menu);
}
