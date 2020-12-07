package controller;

import exception.*;
import model.*;

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
        if (!(Event.getEvents().containsKey(Integer.parseInt(eventID))))
            throw new EventIDNotFoundException();
        Event.getEvents().remove(Integer.parseInt(eventID));
        System.out.println("Event with " + eventID + " ID deleted successfully.");
    }

    public void addSuggestion(String userName, String gameName) throws UsernameNotFound, GameNotFoundException,
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
             throw new UsernameNotFound();
        }
    }

    public void viewSuggestion() {
        for (Map.Entry<Integer, Suggestion> entry : Suggestion.getSuggestions().entrySet()) {
            System.out.println(entry.getValue().getPlayerID() + ": " + entry.getValue().getGameName());
        }
    }

    public void removeSuggestion(String suggestionID) throws SuggestionIDNotFoundException {
        if (Suggestion.getSuggestions().containsKey(Integer.parseInt(suggestionID))) {
            Suggestion.getSuggestions().remove(Integer.parseInt(suggestionID));
            System.out.println("Suggestion with " + suggestionID + " ID deleted successfully.");
        } else
            throw new SuggestionIDNotFoundException();
    }

    public void viewUsers() {
        for (Map.Entry<String, Player> entry : Player.getPlayers().entrySet()) {
            System.out.println("Username: " + entry.getValue().getUsername());
        }
    }

    public void viewUserProfile(String userName) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(userName)) {
            Player player = Player.getPlayers().get(userName);
            System.out.println(player.getFirstName() + " " + player.getLastName() + "\n"
                    + player.getUsername() + "\n"
                    + player.getEmail() + " " + player.getPhoneNumber() + "\n"
                    + player.getScore() + " " + player.getFavoriteGames());
        } else
            throw new UsernameNotFound();
    }
}
