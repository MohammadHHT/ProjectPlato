package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Map;
import java.util.Scanner;

public class FileManager {

    public static void initialize() {
        FileManager.loadAdmin();
        FileManager.loadPlayer();
        FileManager.loadGameLog();
        FileManager.loadEvent();
        FileManager.loadSuggestion();
        FileManager.loadMessage();
    }

    public static void writeDataOnFile() {
        FileManager.writeUser();
        FileManager.writeGameLog();
        FileManager.writeEvent();
        FileManager.writeSuggestion();
        FileManager.writeMessage();
    }

    public static void writeUser() {
        Gson gson = new Gson();
        ArrayList<Admin> admins = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();

        for (Map.Entry<String, User> entry : User.getUsers().entrySet()) {
            if (entry.getValue() instanceof Admin)
                admins.add((Admin) entry.getValue());
            else if (entry.getValue() instanceof Player)
                players.add((Player) entry.getValue());
        }

        Formatter formatter = FileManager.openFileToWrite("resources\\admin.json");
        formatter.format(gson.toJson(admins));
        formatter.close();
        formatter.format(gson.toJson(players));
        formatter.close();
    }

    public static void writeGameLog() {
        Gson gson = new Gson();
        ArrayList<GameLog> gameLogs = new ArrayList<>();

        for (Map.Entry<Long, GameLog> entry : GameLog.getGameLogs().entrySet()) {
            gameLogs.add(entry.getValue());
        }

        Formatter formatter = FileManager.openFileToWrite("resources\\gameLog.json");
        formatter.format(gson.toJson(gameLogs));
    }

    public static void writeEvent() {
        Gson gson = new Gson();
        ArrayList<Event> events = new ArrayList<>();

        for (Map.Entry<Integer, Event> entry : Event.getEvents().entrySet()) {
            events.add(entry.getValue());
        }

        Formatter formatter = FileManager.openFileToWrite("resources\\event.json");
        formatter.format(gson.toJson(events));
    }

    public static void writeSuggestion() {
        Gson gson = new Gson();
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        for (Map.Entry<Long, Suggestion> entry : Suggestion.getSuggestions().entrySet()) {
            suggestions.add(entry.getValue());
        }

        Formatter formatter = FileManager.openFileToWrite("resources\\suggestion.json");
        formatter.format(gson.toJson(suggestions));
    }

    public static void writeMessage() {
        Gson gson = new Gson();
        ArrayList<Message> messages = new ArrayList<>();

        for (Map.Entry<Integer, Message> entry : Message.getAllMessages().entrySet()) {
            messages.add(entry.getValue());
        }

        Formatter formatter = FileManager.openFileToWrite("resources\\message.json");
        formatter.format(gson.toJson(messages));
    }


    public static void loadAdmin() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\admin.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Admin>>(){}.getType();
        ArrayList<User> admins = gson.fromJson(data, foundListType);
        scanner.close();
        User.addUsers(admins);
        Admin.addAdmins(admins);
    }

    public static void loadPlayer() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\player.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Player>>(){}.getType();
        ArrayList<User> players = gson.fromJson(data, foundListType);
        scanner.close();
        User.addUsers(players);
        Player.addPlayers(players);
    }

    public static void loadGameLog() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\gameLog.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<GameLog>>(){}.getType();
        ArrayList<GameLog> gameLogs = gson.fromJson(data, foundListType);
        scanner.close();
        GameLog.addGameLogs(gameLogs);
    }

    public static void loadEvent() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\event.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Event>>(){}.getType();
        ArrayList<Event> events = gson.fromJson(data, foundListType);
        scanner.close();
        for (Event event : events) {
            Event.addEvent(event);
        }
    }

    public static void loadSuggestion() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\suggestion.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Suggestion>>(){}.getType();
        ArrayList<Suggestion> suggestions = gson.fromJson(data, foundListType);
        scanner.close();
        Suggestion.addSuggestions(suggestions);
    }

    public static void loadMessage() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\message.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Message>>(){}.getType();
        ArrayList<Message> messages = gson.fromJson(data, foundListType);
        scanner.close();
        for (Message message : messages) {
            Message.addMessage(message);
        }
    }

    public static Formatter openFileToWrite(String address) {
        Formatter formatter = null;
        try {
            new File(address.substring(0, address.lastIndexOf("\\"))).mkdir();
            formatter = new Formatter(address);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public static Scanner openFileToRead(String address) {
        Scanner scanner = null;
        try {
            File file = new File(address);
            scanner = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scanner;
    }
}