package main.back.controller;

import main.back.account.Admin;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;

public class Server extends WebSocketServer {

    private static final int PORT = 4444;

    private static HashMap<String, WebSocket> conns;

    public Server() {
        super(new InetSocketAddress(PORT));
        conns = new HashMap<>();
    }

    public static HashMap<String, WebSocket> getConns() {
        return conns;
    }

    public void onStart() {
        new Admin("admin", "admin", "admin", "admin", "admin@gamil.com", "989120000000");
    }

    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onMessage(WebSocket conn, String message) {
        System.out.println(message);
        conn.send(resolve(message.trim().split(" "), conn));
    }

    public void onError(WebSocket conn, Exception e) {
//        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    private String resolve(String[] tokens, WebSocket conn) {
        switch (tokens[0]) {
            case "user":
                return UserCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            case "game":
                String ans = GameCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length), conn);
                System.out.println(ans);
                return ans;
            default:
                return "failed command";
        }
    }
}