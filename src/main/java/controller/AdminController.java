package controller;

import exception.EventIDNotFoundException;
import exception.GameNotFoundException;
import exception.InvalidDateException;
import exception.InvalidGameNameException;
import model.Event;
import model.Game;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

public class AdminController {

    public String addEvent(String gameName, Date startDate, Date endDate, long eventScore) throws
            InvalidGameNameException, InvalidDateException {
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
        for (Event allEvent : Database.getAllEvents()) {
            if (allEvent.getStartDate().before(allEvent.getEndDate())) {
                System.out.println("Game name: " + allEvent.getGameName() + "\n" +
                        "Event ID: " + allEvent.getEventID() + "\n" +
                        allEvent.getStartDate() + "to" + allEvent.getEndDate() + "\n" +
                        "Score" + allEvent.getEventScore());
            }
        }
    }

    public void editEvent(String eventID, String field, String newValue) throws GameNotFoundException,
            InvalidDateException, EventIDNotFoundException {
        Event event;
        if ((event = Database.getEventByEventID(eventID)) != null) {
            if (field.equalsIgnoreCase("Game name")){
                for (Game game : Database.getAllGames()) {
                    if (!game.getGameName().equals(newValue))
                        throw new GameNotFoundException();
                        event.setGameName(newValue);
                        System.out.println("Successfully changed to a new value");
                }
            } else if (field.equalsIgnoreCase("Start data")) {
                if (LocalDate.parse(newValue).isBefore(LocalDate.now()) ||
                        LocalDate.parse(newValue).isAfter(event.getEndDate()) ||
                        !newValue.matches("\\d{4}, \\d{2}, \\d{2}"))
                        throw new InvalidDateException();
                        event.setStartDate(LocalDate.parse(newValue));
                        System.out.println("Successfully changed to a new value");
            } else if (field.equalsIgnoreCase("End date")) {
                if (LocalDate.parse(newValue).isBefore(event.getStartDate()) ||
                        !newValue.matches("\\d{4}, \\d{2}, \\d{2}"))
                    throw new InvalidDateException();
                event.setEndDate(LocalDate.parse(newValue));
                System.out.println("Successfully changed to a new value");
            } else if (field.equalsIgnoreCase("Score")) {
                event.setEventScore(Long.parseLong(newValue));
                System.out.println("Successfully changed to a new value");
            }
        } else throw new EventIDNotFoundException();
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
