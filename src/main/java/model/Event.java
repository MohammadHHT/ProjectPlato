package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Event {
    private String gameName;
    private LocalDate startDate;
    private LocalDate endDate;
    private long eventScore;
    private int eventID;
    private static HashMap<Integer, Event> events;

    static {
        events = new HashMap<Integer, Event>();
    }

    public Event(String gameName, LocalDate startDate, LocalDate endDate, long eventScore) {
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventScore = eventScore;
        //EventID
        events.put(eventID, this);
    }

    public static void addEvent(Event event) {
        events.put(event.getEventID(), event);
    }

    public void addNewEvent(){
        //TODO
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

    public void setEventID(int eventID) {
        this.eventID = eventID;
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

    public int getEventID() {
        return eventID;
    }

    public static HashMap<Integer, Event> getEvents() {
        return events;
    }
}
