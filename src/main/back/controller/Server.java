package controller;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Server extends WebSocketServer {

    private static int PORT = 4444;

    private Set<WebSocket> conns;

    public Server() {
        super(new InetSocketAddress(PORT));
        conns = new HashSet<>();
    }

    public void onStart() {

    }

    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conns.add(conn);
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    public void onMessage(WebSocket conn, String message) {
        conn.send(resolve(message.trim().split(" ")));
    }

    public void onError(WebSocket conn, Exception e) {
        //ex.printStackTrace();
        if (conn != null) {
            conns.remove(conn);
            // do some thing if required
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    private String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "user":
                return UserCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            break;
            case "game":
                return GameCommand.resolve(Arrays.copyOfRange(tokens, 1, tokens.length));
            break;
            default:
                return null;
        }
    }
}