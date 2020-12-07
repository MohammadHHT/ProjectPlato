import view.Menu;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {

    }

    private static void start() {
        Menu.push(RegisterMenu.getRegisterMenu());
        RegisterMenu.getRegisterMenu().run();
    }
}
