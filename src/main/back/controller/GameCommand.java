package main.back.controller;

import main.back.account.Player;
import main.back.account.User;
import main.back.game.Game;
import main.back.game.GameLog;
import main.back.game.SeaBattle.SeaBattle;
import org.java_websocket.WebSocket;

import java.util.Arrays;

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
                return join(tokens[1], tokens[2], Long.parseLong(tokens[3]), conn);
            case "gamelogs":
                return gameLogs("dotsandboxes", tokens[1], tokens[2]);
            default:
                return "failed command";
        }
    }

    static String gameLogs(String game, String username, String token) {

        Player player =Player.getPlayers().get(username);
        StringBuilder message = new StringBuilder(player.getLevel() + " " + player.getWins() + " " + player.getDefeats() + " " + player.getScore() + "@");
        for (GameLog log : GameLog.gameLogs.values()) {
            if (log.getGame().equals(game)) {
                switch (log.getResult()) {
                    case WIN:
                        message.append(log.getHost()).append(" ").append(log.getDate()).append("/");
                    case DEFEAT:
                        message.append(log.getGuest()).append(" ").append(log.getDate()).append("/");
                }
            }
        }
        message.append("@");
        for (Player user : Player.getPlayers().values()) {
            message.append(user.getUsername()).append(" ").append(user.getScore()).append("/");
        }
        return message.toString();
    }

    static String create(String username, String token, WebSocket conn) {
        return null;
    }

    static String join(String username, String token, long gameID, WebSocket conn) {
        return null;
    }
}
