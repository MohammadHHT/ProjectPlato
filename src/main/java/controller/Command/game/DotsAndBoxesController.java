package controller.Command.game;

import exception.game.GameNotFound;
import exception.game.NotYourTurn;
import model.DotsAndBoxes.DotsAndBoxes;

import model.DotsAndBoxes.DotsAndBoxes;

public class DotsAndBoxesController {
    private static final DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();

    private DotsAndBoxesController() {
    }

    public static DotsAndBoxesController getDotsAndBoxesController() {
        return dotsAndBoxesController;
    }

    public String occupy(long gameID, int x1, int y1, int x2, int y2, String username) throws NotYourTurn, GameNotFound {
        if (x1 == x2) {
            if (y1 == y2 + 1) {
                y1--;
                y2++;
            } else if (!(y1 == y2 - 1))
                return "Lines can be drawn only between dots that are next to each other";
        } else if (y1 == y2) {
            if (x1 == x2 + 1) {
                x1--;
                x2++;
            } else if (!(x1 == x2 - 1))
                return "Lines can be drawn only between dots that are next to each other";
        } else
            return "Lines can be drawn only between dots that are next to each other";

        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (username == null || dotsAndBoxes.getTurn().getUsername().equals(username)) {
                if (dotsAndBoxes.isEdgeAvailable(x1, y1, x2, y2)) {
                    dotsAndBoxes.occupy(x1, y1, x2, y2);
                    if (dotsAndBoxes.madeAnyBoxes(x1, y1, x2, y2)) {
                        return "Line drawn. you made a box. draw another line.";
                    } else
                        return "Line drawn";
                } else
                    return "you can’t draw a line between these two";
            } else {
                throw new NotYourTurn();
            }
        } else {
            throw new GameNotFound();
        }
    }


    public String endOfMyTurn(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes.hasPlayerMovedInThisTurn()) {
            dotsAndBoxes.turn();
            return "Turn changed successfully";
        } else
            return "in your turn you should draw a line";
    }

    public String showScore(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        return "Host score = " + dotsAndBoxes.getHostScore() + "\n" + "Guest score = " + dotsAndBoxes.getGuestScore();
    }

    public String showAvailableLines(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (dotsAndBoxes.isEdgeAvailable(j, i, j + 1, i)) {
                    stringBuilder.append("(").append(j).append(",").append(i).append(") and (").append(j + 1).append(",").append(i).append(")").append('\n');
                } else if (dotsAndBoxes.isEdgeAvailable(j, i, j, i + 1)) {
                    stringBuilder.append("(").append(j).append(",").append(i).append(") and (").append(j).append(",").append(i+1).append(")").append('\n');
                }
            }
        }
        return stringBuilder.toString();
    }

    public String showTable(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        String table = ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' + dotsAndBoxes.makeTable();
        return table;
    }

    public String whoIsNext(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);

    }

    public String showResult(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);

    }
}
