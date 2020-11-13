package Model;

import java.util.ArrayList;

public class Game {
    private String gameName;
    private String gameID;
    private static ArrayList<Game> games;

    static {
        games = new ArrayList<Game>();
    }

}
