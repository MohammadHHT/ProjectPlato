package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Message {
    private int massageID;
    private int playerID;
    private String message;
    // can not import LocalDateTime!!
    private static HashMap<Integer, Message> allMessages;

    static {
        allMessages = new HashMap<Integer, Message>();
    }

    public Message(int playerID, String message) {
        this.playerID = playerID;
        this.message = message;
        //LocalDateTime
        //massageID
        allMessages.put(massageID, this);
    }

    public static HashMap<Integer, Message> getAllMessages() {
        return allMessages;
    }

    public int getPlayerID() {
        return 0;
    }

    public String getMessage() {
        return message;
    }

    public int getMassageID() {
        return 0;
    }
}
