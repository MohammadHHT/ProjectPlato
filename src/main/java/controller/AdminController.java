package controller;

import exception.*;
import model.*;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.Map;

public class AdminController {

    public String addEvent(String gameName, LocalDate startDate, LocalDate endDate, long eventScore) throws
            InvalidGameNameException, InvalidDateException {
        if (!(Game.getGamesName().contains(gameName)))
            throw new InvalidGameNameException();
        if (startDate.isAfter(endDate) || endDate.isBefore(startDate))
            throw new InvalidDateException();
        new Event(gameName, startDate, endDate, eventScore);
        return "The event was created successfully.";
    }

    public void viewEvent() throws ExpiredEventException {
        for (Map.Entry<Integer, Event> entry : Event.getEvents().entrySet()) {
            if (entry.getValue().getStartDate().isBefore(entry.getValue().getEndDate())) {
                System.out.println("Game name: " + entry.getValue().getGameName() + "\n" +
                        "Event ID: " + entry.getValue().getEventID() + "\n" +
                        entry.getValue().getStartDate() + "to" + entry.getValue().getEndDate() + "\n" +
                        "Score" + entry.getValue().getEventScore());
            } else
                throw new ExpiredEventException();
        }
    }

    public void editEvent(String eventID, String field, String newValue) throws GameNotFoundException,
            InvalidDateException, EventIDNotFoundException {
        Event event = Event.getEvents().get(Integer.parseInt(eventID));
        if (Event.getEvents().containsKey(Integer.parseInt(eventID))) {
            if (field.equalsIgnoreCase("Game name")){
                if (Game.getGamesName().contains(newValue)) {
                    event.setGameName(newValue);
                    System.out.println("Successfully changed to a new value.");
                } else
                    throw new GameNotFoundException();
            } else if (field.equalsIgnoreCase("Start data")) {
                if (LocalDate.parse(newValue).isBefore(LocalDate.now()) ||
                        LocalDate.parse(newValue).isAfter(event.getEndDate()) ||
                        !newValue.matches("\\d{4}, \\d{2}, \\d{2}"))
                        throw new InvalidDateException();
                        event.setStartDate(LocalDate.parse(newValue));
                        System.out.println("Successfully changed to a new value.");
            } else if (field.equalsIgnoreCase("End date")) {
                if (LocalDate.parse(newValue).isBefore(event.getStartDate()) ||
                        !newValue.matches("\\d{4}, \\d{2}, \\d{2}"))
                    throw new InvalidDateException();
                event.setEndDate(LocalDate.parse(newValue));
                System.out.println("Successfully changed to a new value.");
            } else if (field.equalsIgnoreCase("Score")) {
                event.setEventScore(Long.parseLong(newValue));
                System.out.println("Successfully changed to a new value.");
            }
        } else
            throw new EventIDNotFoundException();
    }

    public void removeEvent(String eventID) throws EventIDNotFoundException {
        if (Database.getEventByEventID(eventID) == null)
            throw new EventIDNotFoundException();
        for (Event event : Database.getAllEvents()) {
            if (event.getEventID() == Integer.parseInt(eventID)) {
                Database.allEvents.remove(event);
                System.out.println("Event with " + eventID + " ID deleted successfully.");
            }
        }
    }

    public void addSuggestion(String userName, String gameName) throws UsernameNotFoundException, GameNotFoundException,
            ThisGameHasAlreadyBeenSuggested {
        if (Player.getPlayers().containsKey(userName)) {
            if (Game.getGamesName().contains(gameName)) {
                Suggestion suggestion = new Suggestion(userName, gameName);
                if (!(Player.getPlayers().get(userName).getSuggestions()
                        .contains(String.valueOf(suggestion.getSuggestionID())))) {
                    Player.getPlayers().get(userName).getSuggestions().add(String.valueOf(suggestion.getSuggestionID()));
                } else {
                    throw new ThisGameHasAlreadyBeenSuggested();
                }
            } else {
               throw new GameNotFoundException();
            }
        } else {
             throw new UsernameNotFoundException();
        }
    }

    public void viewSuggestion() {
        for (Suggestion suggestion : Database.getAllSuggestions()) {
            System.out.println(suggestion.getPlayerID() + ": " + suggestion.getGameName());
        }
    }

    public void removeSuggestion(String suggestionID) throws SuggestionIDNotFoundException {
        if (Database.getSuggestionBySuggestionID(suggestionID) == null)
            throw new SuggestionIDNotFoundException();
        for (Suggestion suggestion : Database.getAllSuggestions()) {
            if (suggestion.getSuggestionID() == (Integer.parseInt(suggestionID))) {
                Database.allSuggestions.remove(suggestion);
                System.out.println("Suggestion with " + suggestionID + " ID deleted successfully.");
            }
        }
    }

    public void viewUsers() {
        for (User user : Database.getAllUsers()) {
            if (user instanceof Player) {
                System.out.println(user.getUsername());
            }
        }
    }

    public void viewUserProfile(String userName) throws UsernameNotFoundException {
        if (Database.getUserByUsername(userName) == null)
            throw new UsernameNotFoundException();
        for (User user : Database.getAllUsers()) {
            if (user instanceof Player && user.getUsername().equals(userName)) {
                System.out.println(user.getFirstname() + " " + user.getLastname() + "\n"
                + user.getUsername() + "\n"
                + user.getEmail() + " " + user.getPhoneNumber() + "\n"
                + ((Player) user).getScore() + " " + ((Player) user).getFavoriteGames());
            }
            break;
        }
    }
}
