package Model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String gameName;
    private Date startDate;
    private Date endDate;
    private long eventScore;
    private long eventID;
    private static ArrayList<Event> events;

    static {
        events = new ArrayList<Event>();
    }

    public Event(String gameName, Date startDate, Date endDate, long eventScore) {
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEventScore(long eventScore) {
        this.eventScore = eventScore;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public static void setEvents(ArrayList<Event> events) {
        Event.events = events;
    }

    public String getGameName() {
        return null;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getEventScore() {
        return eventScore;
    }

    public long getEventID() {
        return eventID;
    }

    public static ArrayList<Event> getEvents() {
        return events;
    }
}
