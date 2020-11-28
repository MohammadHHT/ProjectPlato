package controller;

import exception.InvalidDateException;
import exception.InvalidGameNameException;
import model.Event;
import model.Game;

import java.util.Date;

public class AdminController {

    public String addEvent(String gameName, Date startDate, Date endDate, long eventScore) throws InvalidGameNameException, InvalidDateException {
        for (Game allGame : Database.getAllGames()) {
            if (!allGame.getGameName().equals(gameName))
                throw new InvalidGameNameException();
        }
        if (startDate.after(endDate) || endDate.before(startDate))
            throw new InvalidDateException();
            Event event = new Event(gameName, startDate, endDate, eventScore);
            Database.addAllEvents(event);
            return "The event was created successfully.";
    }

    public void viewEvent() {
        //TODO
    }

    public String editEvent(String eventID, String field, String newValue) {
        return ";)";
        //TODO
    }

    public String removeEvent(String eventID) {
        return ";)";
        //TODO
    }

    public String addSuggestion(String userName, String gameName) {
        return ";)";
        //TODO
    }

    public void viewSuggestion() {
        //TODO
    }

    public String removeSuggestion(String suggestionID) {
        return ";)";
        //TODO
    }

    public void viewUsers() {
        //TODO
    }

    public void viewUserProfile(String userName) {
        //TODO
    }
}
