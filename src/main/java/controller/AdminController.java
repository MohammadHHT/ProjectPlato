package controller;

import exception.*;
import model.*;

import java.time.LocalDate;

public class AdminController {

    public String addEvent(String gameName, LocalDate startDate, LocalDate endDate, long eventScore) throws
            InvalidGameNameException, InvalidDateException {
        for (Game allGame : Database.getAllGames()) {
            if (!allGame.getGameName().equals(gameName))
                throw new InvalidGameNameException();
        }
        if (startDate.isAfter(endDate) || endDate.isBefore(startDate))
            throw new InvalidDateException();
            Event event = new Event(gameName, startDate, endDate, eventScore);
            Database.addAllEvents(event);
            return "The event was created successfully.";
    }

    public void viewEvent() {
        for (Event allEvent : Database.getAllEvents()) {
            if (allEvent.getStartDate().isBefore(allEvent.getEndDate())) {
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
                        System.out.println("Successfully changed to a new value.");
                }
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
        } else throw new EventIDNotFoundException();
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

    public void addSuggestion(String userName, String gameName) throws UsernameNotFoundException,
            ThisGameHasAlreadyBeenSuggested {
        if (Database.getUserByUsername(userName) == null)
            throw new UsernameNotFoundException();
//        for (Player player : Player.getPlayers()) {
//            if (player.getUsername().equals(userName)) {
//                for (Suggestion suggestion : player.getSuggestions()) {
//                    if (!suggestion.getGameName().equals(gameName)) {
//                        Database.addAllSuggestions(new Suggestion(userName, gameName));
//                    } else
//                        throw new ThisGameHasAlreadyBeenSuggested();
//                }
//            }
//        }
        //TODO Suggestion & Message class have problem!
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
            if (suggestion.getSuggestionID().equals(suggestionID)) {
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
