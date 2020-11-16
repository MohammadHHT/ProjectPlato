package Model;

import java.util.ArrayList;

public class Message {
    private long massageID;
    private Player player;
    private String message;
    // can not import LocalDateTime!!
    private static ArrayList<Message> allMessages;

    static {
        allMessages = new ArrayList<Message>();
    }

    public Message(Player player, String message) {
        this.player = player;
        this.message = message;
        //LocalDateTime
        //massageID
        allMessages.add(this);
    }

    public static ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public long getMassageID() {
        return massageID;
    }
}
