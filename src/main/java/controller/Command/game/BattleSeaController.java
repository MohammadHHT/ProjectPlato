package controller.Command.game;

public class BattleSeaController {
    private static final BattleSeaController battleSeaController = new BattleSeaController();

    private BattleSeaController() {
    }

    public static BattleSeaController getBattleSeaController() {
        return battleSeaController;
    }

    public void changeCoordination(char ship, int x, int y) {

    }
}
