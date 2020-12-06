package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileManager {

    public static void initialize() {
        FileManager.loadAdmin();
        FileManager.loadPlayer();
        FileManager.loadGameLog();
        FileManager.loadEvent();
    }

    public static void loadAdmin() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\admin.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Admin>>(){}.getType();
        ArrayList<Admin> admins = gson.fromJson(data, foundListType);
        scanner.close();
        for (Admin admin : admins) {
            User.addUser(admin);
        }
    }

    public static void loadPlayer() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\player.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<Player>>(){}.getType();
        ArrayList<Player> players = gson.fromJson(data, foundListType);
        scanner.close();
        for (Player player : players) {
            User.addUser(player);
            Player.addPlayer(player);
        }
    }

    public static void loadGameLog() {
        Gson gson = new Gson();
        Scanner scanner = FileManager.openFileToRead("resources\\gameLog.json");
        String data = scanner.nextLine();
        Type foundListType = new TypeToken<ArrayList<GameLog>>(){}.getType();
        ArrayList<GameLog> gameLogs = gson.fromJson(data, foundListType);
        scanner.close();
        for (GameLog gameLog : gameLogs) {
            GameLog.addGameLog(gameLog);
        }
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