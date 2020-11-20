package Controller;

import Model.*;

import java.util.ArrayList;

public class Database {
    static ArrayList<User> allUsers = new ArrayList<>();
    static ArrayList<Game> allGames = new ArrayList<>();
    static ArrayList<Suggestion> allSuggestions = new ArrayList<>();
    static ArrayList<Message> allMessages = new ArrayList<>();
    static ArrayList<GameLog> allGameLogs = new ArrayList<>();

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }

    public static ArrayList<Suggestion> getAllSuggestions() {
        return allSuggestions;
    }

    public static ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public static ArrayList<GameLog> getAllGameLogs() {
        return allGameLogs;
    }

    public static void addAllUsers(User user) {
        allUsers.add(user);
    }

    public static void addAllGames(Game game) {
        allGames.add(game);
    }

    public static void addAllSuggestions(Suggestion suggestion) {
        allSuggestions.add(suggestion);
    }

    public static void addAllMessages(Message message) {
        allMessages.add(message);
    }

    public static void addAllAccounts(GameLog gameLog) {
        allGameLogs.add(gameLog);
    }
}
