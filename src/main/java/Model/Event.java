package Model;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private Game gameName;
    private Date startDate;
    private Date endDate;
    private long eventScore;
    private long eventID;
    private static ArrayList<Event> events;

    static {
        events = new ArrayList<Event>();
    }

    public Event(Game gameName, Date startDate, Date endDate, long eventScore, long eventID) {
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventScore = eventScore;
        this.eventID = eventID;
    }
}
