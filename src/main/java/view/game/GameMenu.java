package view.game;

import view.Back;
import view.Client;
import view.Menu;

public class GameMenu extends Menu implements Back {
    private static final GameMenu gameMenu = new GameMenu();

    private GameMenu() {
    }

    public static GameMenu getGameMenu() {
        return gameMenu;
    }

    private void open() {
        Client.getClient().send("game names");
        System.out.print("Game Name (" + Client.getClient().getResponse() + "): >");
        String game = scanner.nextLine().replaceAll(" ", "");
        Client.getClient().send("game open " + username + " " + game);

        if (Client.getClient().getResponse().length() == 1) {
            gameID = Long.parseLong(Client.getClient().getResponse());
            switch (game) {
                case "DotsAndBoxes":
                    next(DotsAndBoxesMenu.getDotsAndBoxesMenu());
                    break;
                case "BattleSea":
                    next(BattleSeaMenu.getBattleSeaMenu());
                    break;
            }
        } else {
            System.out.println(Client.getClient().getResponse());
            run();
        }
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
                    return;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {
        menus.push(menu);
        menu.run();
    }
}
