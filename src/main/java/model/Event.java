package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String gameName;
    private LocalDate startDate;
    private LocalDate endDate;
    private long eventScore;
    private int eventID;
    private static ArrayList<Event> events;

    static {
        events = new ArrayList<Event>();
    }

    public Event(String gameName, LocalDate startDate, LocalDate endDate, long eventScore) {
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventScore = eventScore;
        //EventID
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

    public static void setEvents(ArrayList<Event> events) {
        Event.events = events;
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

    public static ArrayList<Event> getEvents() {
        return events;
    }
}
