package View;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Menu {
    protected static ArrayList<Menu> tree = new ArrayList<>();

    protected static Scanner scanner = new Scanner(System.in);

    public abstract void run();
}
