package view;

import java.util.Scanner;
import java.util.Stack;

abstract class Menu {
    private static Stack<Menu> menus;
    protected static Scanner scanner;
    protected static String username;

    static  {
        menus = new Stack<>();
        scanner = new Scanner(System.in);
        username = null;
    }

    protected void push(Menu menu) {
        menus.push(menu);
    }

    protected void pop() {
        menus.pop();
    }

    public abstract void run();

    public abstract void next();
}
