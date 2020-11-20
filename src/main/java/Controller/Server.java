package Controller;

public class Server {
    private static final Server server = new Server();

    private Server() {}

    public static Server getServer() {
        return server;
    }

    public void receive(String content) {
        //TODO
    }

    public void receive(String type, String content) {
        //TODO
    }

    public void send(String content) {
        //TODO
    }

    public void send(String type, String content) {
        //TODO
    }
}
