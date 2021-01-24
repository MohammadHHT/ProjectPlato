package main.back.controller;

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
