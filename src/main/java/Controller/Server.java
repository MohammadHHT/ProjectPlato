package Controller;

public class Server {
    private static final Server server = new Server();

    private String response;

    private Server() {}

    public static Server getServer() {
        return server;
    }

    public void receive(String content) {
        //TODO
    }

    public void send(String content) {
        //TODO
    }
}
