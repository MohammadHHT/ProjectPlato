package controller.Command.game;

import exception.game.NotYourTurn;

public class DotsAndBoxesController {
    private static final DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();

    private DotsAndBoxesController() {
    }

    public static DotsAndBoxesController getDotsAndBoxesController() {
        return dotsAndBoxesController;
    }

    public void occupy(long gameID, int x1, int y1, int x2, int y2) throws NotYourTurn {

    }
}
