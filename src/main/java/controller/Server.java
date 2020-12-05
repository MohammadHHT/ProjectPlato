package controller;

import controller.Command.Command;
import view.Client;

public class Server {
    private static final Server server = new Server();

    private Server() {}

    public static Server getServer() {
        return server;
    }

    public void receive(String content) {
        Command.getCommand().resolveCommand(content.trim().split(" "));
    }

    public void send(String content) {
        Client.getClient().receive(content);
    }
}
