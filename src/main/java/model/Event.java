package model;

import java.time.LocalDate;
import java.util.*;

public class Event {
    private static HashMap<Long, Event> events;

    private String gameName;
    private LocalDate start;
    private LocalDate end;
    private long score;
    private long eventID;

    static {
        events = new HashMap<>();
    }

    public Event(String gameName, LocalDate start, LocalDate end, long score) {
        eventID = IDGenerator();
        events.put(eventID, this);
        this.gameName = gameName;
        this.start = start;
        this.end = end;
        this.score = score;
    }

    private long IDGenerator() {
        Random random = new Random();
        return random.nextLong();
    }

    public static void addEvents(ArrayList<Event> events) {
        for (Event e : events) {
            Event.events.put(e.eventID, e);
        }
    }

    public void deleteEvent(){
        //TODO
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getGameName() {
        return null;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public long getEventID() {
        return eventID;
    }

    public static HashMap<Long, Event> getEvents() {
        return events;
    }
}
