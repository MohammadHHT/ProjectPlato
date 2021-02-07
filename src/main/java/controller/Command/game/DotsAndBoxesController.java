package controller.Command.game;

import exception.game.GameNotFound;
import exception.game.NotYourTurn;
import model.DotsAndBoxes.DotsAndBoxes;

import model.Game;
import model.GameLog;
import model.Player;

import java.time.LocalDate;
import java.util.Date;

public class DotsAndBoxesController {
    private static final DotsAndBoxesController dotsAndBoxesController = new DotsAndBoxesController();

    private DotsAndBoxesController() {
    }

    public static DotsAndBoxesController getDotsAndBoxesController() {
        return dotsAndBoxesController;
    }

    public String join(long gameID, String username) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (Player.getPlayers().containsKey(username)) {
                dotsAndBoxes.join(Player.getPlayers().get(username));
                return "joined";
            } else
                return "wrong username";
        } else {
            throw new GameNotFound();
        }
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


    public String endOfMyTurn(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.hasPlayerMovedInThisTurn()) {
                dotsAndBoxes.turn();
                return "Turn changed successfully";
            } else
                return "in your turn you should draw a line";
        } else {
            throw new GameNotFound();
        }
    }

    public String showScore(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            return dotsAndBoxes.getHost().getUsername() + " score = " + dotsAndBoxes.getHostScore() + "\n" + dotsAndBoxes.getGuest().getUsername() + " score = " + dotsAndBoxes.getGuestScore();
        } else {
            throw new GameNotFound();
        }
    }

    public String showAvailableLines(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {

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
        } else {
            throw new GameNotFound();
        }
    }

    public String showTable(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            return ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                    ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                    ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' +
                    ".   .   .   .   .   .   .   ." + '\n' + ".   .   .   .   .   .   .   ." + '\n' + dotsAndBoxes.makeTable();
        } else {
            throw new GameNotFound();
        }
    }

    public String whoIsNext(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            return "It's " + dotsAndBoxes.getTurn().getUsername() + "'s turn";
        } else {
            throw new GameNotFound();
        }
    }

    public String showResult(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.isBoardFull()) {
                Player winner = dotsAndBoxes.judge();
                if (winner != null)
                    return showScore(gameID) + '\n' + winner.getUsername() + "is the winner";
                else
                    return showScore(gameID) + '\n' + "Its a draw";
            } else
                return "Game is not over yet";
        } else {
            throw new GameNotFound();
        }
    }

    public String end(long gameID) throws GameNotFound {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.isBoardFull()) {
                GameLog.Result result;
                if (dotsAndBoxes.judge() == null)
                    result = GameLog.Result.DRAW;
                else if (dotsAndBoxes.judge().equals(dotsAndBoxes.getHost()))
                    result = GameLog.Result.WIN;
                else
                    result = GameLog.Result.DEFEAT;
                new GameLog("dotsandboxes", dotsAndBoxes.getHost(), dotsAndBoxes.getGuest(), LocalDate.now(), result);
                return "Back to the game menu";
            } else {
                new GameLog("dotsandboxes", dotsAndBoxes.getHost(), dotsAndBoxes.getGuest(), LocalDate.now(), dotsAndBoxes.winByForfeit().equals(dotsAndBoxes.getHost())? GameLog.Result.WIN: GameLog.Result.DEFEAT);
                return dotsAndBoxes.winByForfeit().getUsername() + "won by forfeit" + '\n' + "Back to the game menu";
            }

        } else {
            throw new GameNotFound();
        }
    }
}
