package Controller;

import static Controller.Database.*;
import Model.Admin;
import Model.Player;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileManager {
    public static void initialize(){
        //TODO
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
        //TODO
    }



}
