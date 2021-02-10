package main.back.controller;

import main.back.account.Player;
import main.back.account.User;
import main.back.game.DotsAndBoxes.DotsAndBoxes;
import main.back.game.Game;
import main.back.game.GameLog;
import main.back.game.Result;
import main.back.game.SeaBattle.SeaBattle;
import org.java_websocket.WebSocket;

import java.util.*;

public interface GameCommand {
    static String resolve(String[] tokens, WebSocket conn) {
        switch (tokens[0]) {
            case "battle":
                return BattleCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length), conn);
            case "dots":
                return DotsCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length), conn);
            default:
                return "failed command";
        }
    }
}

interface BattleCommand {
    static String resolve(String[] tokens, WebSocket conn) {
        switch (tokens[0]) {
            case "create":
                return create(tokens[1], tokens[2], conn);
            case "join":
                return join(tokens[1], tokens[2], Long.parseLong(tokens[3]), conn);
            case "arrange":
                arrange(tokens[1], tokens[2], Long.parseLong(tokens[3]), Arrays.stream(Arrays.copyOfRange(tokens, 4, tokens.length)).mapToInt(Integer::parseInt).toArray());
                return null;
            case "gamelogs":
                return DotsCommand.gameLogs("seebattle", tokens[1], tokens[2]);
            default:
                return "failed command";
        }
    }

    static String create(String username, String token, WebSocket conn) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {
            Server.getConns().put(username, conn);
            return String.valueOf(new SeaBattle(player).getGameID());
        }
        return null;
    }

    static String join(String username, String token, long gameID, WebSocket conn) {
        Player player = Player.getPlayers().get(username);
        SeaBattle seaBattle = (SeaBattle) Game.getGames().get(gameID);
        if (player.getToken().equals(token)) {
            Server.getConns().put(username, conn);
            seaBattle.join(player);
            return String.valueOf(seaBattle.getGameID());
        }
        return null;
    }

    static void arrange(String username, String token, long gameID, int[] cells) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {
            ((SeaBattle) Game.getGames().get(gameID)).getGrids().get(player).setShips(Arrays.copyOfRange(cells, 0, 8), Arrays.copyOfRange(cells, 8, 14), Arrays.copyOfRange(cells, 14, 18), Arrays.copyOfRange(cells, 18, 20), Arrays.copyOfRange(cells, 20, 22));
        }
    }

}

interface DotsCommand {
    static String resolve(String[] tokens, WebSocket conn) {
        switch (tokens[0]) {
            case "create":
                return create(tokens[1], tokens[2], conn);
            case "join":
                return join(tokens[1], tokens[2], Long.parseLong(tokens[3]), tokens[4], conn);
            case "gamelogs":
                return gameLogs("dotsandboxes", tokens[1], tokens[2]);
            case "line":
                return occupy(Long.parseLong(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
            case "turn":
                return turn(Long.parseLong(tokens[1]));
            case "end":
                return end(Long.parseLong(tokens[1]));
            default:
                return "failed command";
        }
    }

    static String gameLogs(String game, String username, String token) {
        if (User.getUsers().get(username).getToken().equals(token)) {

            Player player = Player.getPlayers().get(username);
            StringBuilder message = new StringBuilder(player.getLevel() + " " + player.getWins() + " " + player.getDefeats() + " " + player.getScore() + "@");


            HashMap<String, Long> players = new HashMap<>();
            for (Player user : Player.getPlayers().values()) {
                players.put(user.getUsername(), user.getScore());
            }
            Object[] a = players.entrySet().toArray();
            Arrays.sort(a, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((Map.Entry<String, Long>) o2).getValue()
                            .compareTo(((Map.Entry<String, Long>) o1).getValue());
                }
            });
            for (Object o : a) {
                message.append(((Map.Entry<String, Long>) o).getKey()).append(" ").append(((Map.Entry<String, Long>) o).getValue()).append("/");
            }
            /*List<Player> players = new ArrayList<>(Player.getPlayers().values());
            Collections.sort(players);*/
            /*for (Player user : players) {
                message.append(user.getUsername()).append(" ").append(user.getScore()).append("/");
            }*/
            message.replace(message.length()-1, message.length(), "@");

            if (!GameLog.getGameLogs().isEmpty()) {
                for (GameLog log : GameLog.getGameLogs().values()) {
                    if (log.getGame().equals(game)) {
                        switch (log.getResult()) {
                            case WIN:
                                message.append(log.getHost()).append("-").append(log.getDate().getHour()).append(":").append(log.getDate().getMinute()).append("/");
                            case DEFEAT:
                                message.append(log.getGuest()).append("-").append(log.getDate().getHour()).append(":").append(log.getDate().getMinute()).append("/");
                        }
                    }
                }
            }
//            message.append("@");

            message.delete(message.length()-1,message.length());
            return message.toString();

        }
        return "token invalid";
//        return "5 15 2 1200@mamad 12:16am/qoli 05:54pm@ali 254/mahdi 658";
    }

    static String create(String username, String token, WebSocket conn) {
        if (User.getUsers().get(username).getToken().equals(token))
            return String.valueOf(new DotsAndBoxes(Player.getPlayers().get(username)).getGameID());
        return null;
    }


    static String join(String username, String token, long gameID, String oppUsername, WebSocket conn) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
            if (dotsAndBoxes != null) {
                dotsAndBoxes.join(Player.getPlayers().get(oppUsername));
                return "joined";
            } else return "game not found";
        }
        return "token not valid";
    }

    static String occupy(long gameID, int x1, int y1, int x2, int y2) {
        /*if (x1 == x2) {
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
            return "you can’t draw a line between these two";*/

        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.isEdgeAvailable(x1, y1, x2, y2)) {
                dotsAndBoxes.occupy(x1, y1, x2, y2);
                return "Line drawn";
            } else
                return "edge not available";
        }
        return "game not found";
    }

    static String turn(long gameID) {
        DotsAndBoxes.getDotsAndBoxes().get(gameID).turn();
        return null;
    }

    static String end(long gameID) {
        DotsAndBoxes dotsAndBoxes = DotsAndBoxes.getDotsAndBoxes().get(gameID);
        if (dotsAndBoxes != null) {
            if (dotsAndBoxes.isBoardFull()) {
                Result result = dotsAndBoxes.judge().equals(dotsAndBoxes.getHost()) ? Result.WIN : Result.DEFEAT;
                new GameLog("dotsandboxes", dotsAndBoxes.getHost().getUsername(), dotsAndBoxes.getGuest().getUsername(), result);
                return dotsAndBoxes.judge().getUsername() + " Won!" + '\n' + "Back to the game menu";
            } else {
                Result result = dotsAndBoxes.winByForfeit().equals(dotsAndBoxes.getHost()) ? Result.WIN : Result.DEFEAT;
                new GameLog("dotsandboxes", dotsAndBoxes.getHost().getUsername(), dotsAndBoxes.getGuest().getUsername(), result);
                return dotsAndBoxes.winByForfeit().getUsername() + " won by forfeit" + '\n' + "Back to the game menu";
            }

        }
        return null;
    }

}
