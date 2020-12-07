package view;

import java.util.Scanner;
import java.util.Stack;

public abstract class Menu {
    protected static Stack<Menu> menus;
    protected static Scanner scanner;
    protected static String username;

    static {
        menus = new Stack<>();
        scanner = new Scanner(System.in);
        username = null;
    }

    public static void push(Menu menu) {
        menus.push(menu);
    }

    public static void pop() {
        menus.pop();
    }

    public abstract void run();

    public abstract void next(Menu menu);
}
