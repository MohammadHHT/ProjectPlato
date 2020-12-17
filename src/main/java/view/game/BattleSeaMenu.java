package view.game;

import view.Client;
import view.Menu;

public class BattleSeaMenu extends Menu implements Game {
    private static final BattleSeaMenu battleSeaMenu = new BattleSeaMenu();

    private BattleSeaMenu() {
    }

    public static BattleSeaMenu getBattleSeaMenu() {
        return battleSeaMenu;
    }

    private void changeCoordinate() {
        System.out.print("Ship Name: >");
        char ship = (char) scanner.nextByte();
        scanner.nextLine();
        if (ship == 'A' || ship == 'B' || ship == 'C' || ship == 'D' || ship == 'E' || ship == 'F') {
            System.out.print("Coordinates (x y): >");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            scanner.nextLine();
            if (x > 0 && x < 11 && y > 0 && y < 11) {
                Client.getClient().send("game BattleSea changeCoordination " + ship + " " + x + " " + y);
                System.out.println(Client.getClient().getResponse());
            } else {
                System.out.println("Wrong coordination!");
            }
        } else {
            System.out.println("Wrong ship name!");
        }
    }

    @Override
    public void run() {
        while (true) {
            switch (scanner.nextLine().trim()) {
                case "turn":
                    turn();
                    break;
                case "change coordinate":
                    changeCoordinate();
                    break;
            }
        }
    }

    @Override
    public void next(Menu menu) {

    }
}
