package main.back.controller;

import main.back.account.Player;
import main.back.game.Game;
import main.back.game.SeaBattle.SeaBattle;

import java.util.Arrays;

public interface GameCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "battle":
                return BattleCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            case "dots":
                return DotsCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            default:
                return "failed command";
        }
    }
}

interface BattleCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "create":
                return create(tokens[1], tokens[2]);
            case "join":
                return join(tokens[1], tokens[2], Long.parseLong(tokens[3]));
            case "arrange":
                arrange(tokens[1], tokens[2], Long.parseLong(tokens[3]), Arrays.stream(Arrays.copyOfRange(tokens, 4, tokens.length)).mapToInt(Integer::parseInt).toArray());
                return null;
            default:
                return "failed command";
        }
    }

    static String create(String username, String token) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {

        }
        return null;
    }

    static String join(String username, String token, long gameID) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {

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
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "create":
                return create(tokens[1]);
            case "join":
                return join(tokens[1], tokens[2]);
            default:
                return "failed command";
        }
    }

    static String create(String username) {
        return null;
    }

    static String join(String username, String gameID) {
        return null;
    }
}
