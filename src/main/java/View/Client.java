package View;

import Controller.Server;

public class Client {
    private static final Client client = new Client();

    private String response;

    private Client() {}

    public static Client getClient() {
        return client;
    }

    public String getResponse() {
        return response;
    }

    public void send(String content) {
        Server.getServer().receive(content);
    }

    public void recieve(String content) {

    }
}
