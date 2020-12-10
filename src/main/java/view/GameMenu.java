package view;

public class GameMenu extends Menu implements Back {
    private static final GameMenu gameMenu = new GameMenu();

    private GameMenu() {
    }

    public static GameMenu getGameMenu() {
        return gameMenu;
    }

    private void open() {
        System.out.print("Game Name (Battle Sea • Dots And Boxes): >");
        String game = scanner.nextLine().replaceAll(" ", "");

        //TODO
    }


    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine()) {
                case "open":
                    open();
                    return;
                case "back":
                    back();
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {

    }
}
