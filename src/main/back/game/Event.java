package main.back.game;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Event {

    private static HashMap<Long, Event> events;

    private long eventID;
    private String game;
    private LocalDateTime start;
    private LocalDateTime end;
    private int score;

    static {
        events = new HashMap<>();
    }

    public Event(String game, LocalDateTime start, LocalDateTime end, int score) {
        eventID = (new Random()).nextLong();
        events.put(eventID, this);
        this.game = game;
        this.start = start;
        this.end = end;
        this.score = score;
    }

    public static void addEvents(ArrayList<Event> events) {
        for (Event e : events) {
            Event.events.put(e.eventID, e);
        }
    }

    public static HashMap<Long, Event> getEvents() {
        return events;
    }

    public void deleteEvent(long eventID){
        if (events.containsKey(eventID)) {
            events.remove(eventID);
        }
    }

    public String getGame() {
        return null;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getScore() {
        return score;
    }

    @Override
    public String toString() {
        return game + " " + score + " " + start.getYear() + " " + start.getMonth() + " " + start.getDayOfMonth() + start.getHour() + " " + start.getMinute() + " " +
        end.getYear() + " " + end.getMonth() + " " + end.getDayOfMonth() + " " + end.getHour() + " " + end.getMinute();
    }
}
