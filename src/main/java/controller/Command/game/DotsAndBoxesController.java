package controller.Command.game;

import exception.game.GameNotFound;
import exception.game.NotYourTurn;
import model.DotsAndBoxes.DotsAndBoxes;

public class DotsAndBoxesController {
    private static final DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();

    private DotsAndBoxesController() {
    }

    public static DotsAndBoxesController getDotsAndBoxesController() {
        return dotsAndBoxesController;
    }

    public void occupy(long gameID, int x1, int y1, int x2, int y2, String username) throws NotYourTurn, GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.getTurn().getUsername().equals(username)) {

            } else {
                throw new NotYourTurn();
            }
        } else {
            throw new GameNotFound();
        }
    }
}
