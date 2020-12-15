package view;

import java.util.Scanner;
import java.util.Stack;

public abstract class Menu {
    public enum Rank {
        ADMIN, PLAYER
    }

    public static Stack<Menu> menus = new Stack<>();
    public static Scanner scanner = new Scanner(System.in);
    public static String username = null;
    public static Rank rank = null;
    public static long gameID = -1;                              // when a game starts, gameID will be set

    public abstract void run();

    public abstract void next(Menu menu);
}
