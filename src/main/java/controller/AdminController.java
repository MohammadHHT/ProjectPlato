package controller;

import exception.*;
import model.*;

import java.time.LocalDate;
import java.util.Map;

public class AdminController {
    private static final AdminController adminController = new AdminController();

    private AdminController() {
    }

    public static AdminController getAdminController() {
        return adminController;
    }

    public void addEvent(String game, int syear, int smonth, int sday, int fyear, int fmonth, int fday, long score) throws InvalidGameNameException {
        if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
            new Event(game, LocalDate.of(syear, smonth, sday), LocalDate.of(fyear, fmonth, fday), score);
        } else {
            throw new InvalidGameNameException();
        }
    }

    public String showEvents() {
        StringBuilder tmp = new StringBuilder();
        for (Event e : Event.getEvents().values()) {
            tmp.append(e.getEventID()).append(e.getGameName()).append(" ").append(e.getStartDate()).append(" ").append(e.getEndDate()).append(" ").append(e.getEventScore()).append("\n");
        }
        return tmp.toString().trim();
    }

    public void editEvent(String eventID, int score) throws EventIDNotFound {
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
            throw new EventIDNotFound();
    }

    public void editEvent(String eventID, String field, int year, int month, int day) throws EventIDNotFound {

    }

    public void removeEvent(String eventID) throws EventIDNotFound {
        if (!(Event.getEvents().containsKey(Integer.parseInt(eventID))))
            throw new EventIDNotFound();
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
                    + player.getEmail() + " " + player.getPhone() + "\n"
                    + player.getScore() + " " + player.getFavoriteGames());
        } else
            throw new UsernameNotFound();
    }
}
