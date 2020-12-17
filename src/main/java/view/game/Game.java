package view.game;

import view.Client;
import view.Menu;

public interface Game {
    default void turn() {
        Client.getClient().send("game turn " + Menu.gameID);
        System.out.println(Client.getClient().getResponse());
    }

    default void update() {

    }
}
