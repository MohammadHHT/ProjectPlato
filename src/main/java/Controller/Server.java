package Controller;

public class Server {
    private static final Server server = new Server();

    private Server() {}

    public static Server getServer() {
        return server;
    }

    public void recieve(String content) {

    }

    public void recieve(String type, String content) {

    }

    public void send(String content) {

    }

    public void send(String type, String content) {

    }
}
