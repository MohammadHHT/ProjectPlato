package view;

import java.util.Scanner;
import java.util.Stack;

abstract class Menu {
    private static Stack<Menu> menus = new Stack<>();
    protected static Scanner scanner = new Scanner(System.in);

    protected void push(Menu menu) {
        menus.push(menu);
    }

    protected void pop() {
        menus.pop();
    }

    public abstract void run();

    public abstract void next();
}
