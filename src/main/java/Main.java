import controller.FileManager;
import view.Menu;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {
        FileManager.initialize();
        start();
    }

    private static void start() {
        Menu.menus.push(RegisterMenu.getRegisterMenu());
        RegisterMenu.getRegisterMenu().run();
    }

    public static void exit() {
        FileManager.writeDataOnFile();
        System.exit(1);
    }
}
