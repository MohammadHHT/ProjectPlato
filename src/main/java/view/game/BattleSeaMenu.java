package view.game;

import view.Menu;

public class BattleSeaMenu extends Menu implements Game {
    private static final BattleSeaMenu battleSeaMenu = new BattleSeaMenu();

    private BattleSeaMenu() {
    }

    public static BattleSeaMenu getBattleSeaMenu() {
        return battleSeaMenu;
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "turn":
                    turn();
                    break;
                case ""
            }
        }
    }

    @Override
    public void next(Menu menu) {

    }
}
