package view;

public class GameMenu extends Menu {
    private static final GameMenu gameMenu = new GameMenu();

    private GameMenu() {
    }

    public static GameMenu getGameMenu() {
        return gameMenu;
    }


    @Override
    public void run() {

    }

    @Override
    public void next(Menu menu) {

    }
}
