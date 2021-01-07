package controller;

import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Server extends WebSocketServer {

    private static int PORT = 4444;

    private Set<WebSocket> connections;

    public Server() {
        super(new InetSocketAddress(PORT));
        connections = new HashSet<>();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        connections.add(connection);
        System.out.println("New connection from " + connection.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        connections.remove(connection);
        System.out.println("Closed connection to " + connection.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        System.out.println("Message from client: " + message);
        Gson gson = new Gson();
    }

    @Override
    public void onError(WebSocket connection, Exception e) {
        //ex.printStackTrace();
        if (connection != null) {
            connections.remove(connection);
            // do some thing if required
        }
        System.out.println("ERROR from " + connection.getRemoteSocketAddress().getAddress().getHostAddress());
    }
}