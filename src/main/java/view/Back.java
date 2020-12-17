package view;

public interface Back {
    default void back() {
        int size = Menu.menus.size();
        if (size > 1) {
            Menu.menus.pop();
            Menu.menus.get(size - 1).run();
        } else {
            System.err.println("Close app?");
        }
    }
}
