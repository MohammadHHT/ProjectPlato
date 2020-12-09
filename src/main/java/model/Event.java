package model;

import java.time.LocalDate;
import java.util.*;

public class Event {
    private static HashMap<Long, Event> events;

    private String gameName;
    private LocalDate startDate;
    private LocalDate endDate;
    private long eventScore;
    private long eventID;

    static {
        events = new HashMap<>();
    }

    public Event(String gameName, LocalDate startDate, LocalDate endDate, long eventScore) {
        eventID = IDGenerator();
        events.put(eventID, this);
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventScore = eventScore;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEventScore(long eventScore) {
        this.eventScore = eventScore;
    }

    public String getGameName() {
        return null;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getEventScore() {
        return eventScore;
    }

    public long getEventID() {
        return eventID;
    }

    public static AbstractMap<Long, Event> getEvents() {
        return events;
    }
}
