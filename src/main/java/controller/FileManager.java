package controller;

import static controller.Database.*;
import model.Admin;
import model.Player;
import model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileManager {
    public static void initialize(){
        loadUsersFromFile("Admin");
        loadUsersFromFile("Player");
    }

    public static void loadUsersFromFile(String place) {
        Gson gson = new Gson();
        File file = new File("Data\\Accounts\\"+place+".json");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ArrayList<User> array;
        Type type;
        if (place.equals("admin")) {
            type = new TypeToken<ArrayList<Admin>>(){}.getType();
        } else  {
            type = new TypeToken<ArrayList<Player>>(){}.getType();
        }
        array = gson.fromJson(bufferedReader, type);
        allUsers.addAll(array);

    }

    public static void saveUsersOnFile() {
        ArrayList<Object> admin = new ArrayList<Object>();
        ArrayList<Object> player = new ArrayList<Object>();
        for (User user: allUsers) {
            if (user instanceof Player) {
                player.add(user);
            } else if (user instanceof Admin) {
                admin.add(user);
            }
        }
        saveArrayOnFile(admin, "Accounts\\Admin");
        saveArrayOnFile(player, "Accounts\\Player");
    }

    private static void saveArrayOnFile(ArrayList<Object> array, String name) {
        File file = new File("Data\\" + name + ".json");
        file.getParentFile().mkdirs();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            Gson gson = new Gson();
            String json = gson.toJson(array);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
