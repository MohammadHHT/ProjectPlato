package controller.Command.game;

import exception.game.GameNotFound;
import exception.game.NotYourTurn;
import model.DotsAndBoxes.DotsAndBoxes;

import model.Player;

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
                return "you can’t draw a line between these two";
        } else if (y1 == y2) {
            if (x1 == x2 + 1) {
                x1--;
                x2++;
            } else if (!(x1 == x2 - 1))
                return "you can’t draw a line between these two";
        } else
            return "you can’t draw a line between these two";

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
        return dotsAndBoxes.getHost().getUsername() + " score = " + dotsAndBoxes.getHostScore() + "\n" + dotsAndBoxes.getGuest().getUsername() + " score = " + dotsAndBoxes.getGuestScore();
    }

    public String showAvailableLines(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (dotsAndBoxes.isEdgeAvailable(j, i, j + 1, i)) {
                    stringBuilder.append("(").append(j).append(",").append(i).append(") and (").append(j + 1).append(",").append(i).append(")").append('\n');
                } else if (dotsAndBoxes.isEdgeAvailable(j, i, j, i + 1)) {
                    stringBuilder.append("(").append(j).append(",").append(i).append(") and (").append(j).append(",").append(i + 1).append(")").append('\n');
                }
            }
        }
        return stringBuilder.toString();
    }

    public String showTable(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        return ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' + dotsAndBoxes.makeTable();
    }

    public String whoIsNext(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        return "It's " + dotsAndBoxes.getTurn().getUsername() + "'s turn";
    }

    public String showResult(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes.isBoardFull()) {
            Player winner = dotsAndBoxes.judge();
            if (winner != null)
                return showScore(gameID) + '\n' + winner.getUsername() + "is the winner";
            else
                return showScore(gameID) + '\n' + "Its a draw";
        } else
            return "Game is not over yet";
    }

    public String end(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes.isBoardFull()) {
            return "Back to the game menu";
        } else {
            return dotsAndBoxes.winByForfeit().getUsername() + "won by forfeit" + '\n' + "Back to the game menu";
        }

    }
}
