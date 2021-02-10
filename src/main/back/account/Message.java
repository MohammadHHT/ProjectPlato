package main.back.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Message {

    private static HashMap<Long, Message> messages;

    private long messageID;
    private String player;
    private String message;
    private LocalDateTime date;

    static {
        messages = new HashMap<>();
    }

    public Message(String player, String message) {
        messageID = (new Random()).nextLong();
        messages.put(messageID, this);
        this.player = player;
        this.message = message;
        date = LocalDateTime.now();
        Player.getPlayers().get(player).getMessages().add(messageID);
    }

    public void addMessages(ArrayList<Message> messages) {
        for (Message m : messages) {
            Message.messages.put(m.messageID, m);
        }
    }

    public static HashMap<Long, Message> getMessages() {
        return messages;
    }

    public String getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
