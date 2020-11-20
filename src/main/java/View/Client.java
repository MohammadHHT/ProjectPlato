package View;

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

    }

    public void send(String type, String content) {

    }

    public void recieve(String content) {

    }

    public void recieve(String type, String content) {

    }
}
