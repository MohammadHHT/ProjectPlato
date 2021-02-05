package main.back.controller;

import main.back.account.Player;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Server extends WebSocketServer {

    private static final int PORT = 4444;

    private HashMap<String, WebSocket> conns;

    public Server() {
        super(new InetSocketAddress(PORT));
        conns = new HashMap<>();
    }

    public void onStart() {

    }

    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conns.put();
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onMessage(WebSocket conn, String message) {
        System.out.println(message);
        conn.send(resolve(message.trim().split(" ")));
    }

    public void onError(WebSocket conn, Exception e) {
        if (conn != null) {
            conns.remove(conn);
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    private String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "user":
                return UserCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            case "game":
                return GameCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            default:
                return "failed command";
        }
    }
}