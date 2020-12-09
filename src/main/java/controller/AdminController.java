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
            tmp.append(e.getEventID()).append(e.getGameName()).append(" ").append(e.getStart()).append(" ").append(e.getEnd()).append(" ").append(e.getScore()).append("\n");
        }
        return tmp.toString().trim();
    }

    public void editEvent(long eventID, int score) throws EventIDNotFound {
        if (Event.getEvents().containsKey(eventID)) {
            Event.getEvents().get(eventID).setScore(score);
        } else {
            throw new EventIDNotFound();
        }
    }

    public void editEvent(long eventID, String field, int year, int month, int day) throws EventIDNotFound {
        if (Event.getEvents().containsKey(eventID)) {
            Event event = Event.getEvents().get(eventID);
            if (field.equals("start")) {
                event.setStart(LocalDate.of(year, month, day));
            } else {
                event.setEnd(LocalDate.of(year, month, day));
            }
        } else {
            throw new EventIDNotFound();
        }
    }

    public void removeEvent(long eventID) throws EventIDNotFound {
        if (Event.getEvents().containsKey(eventID)) {
            Event.getEvents().remove(eventID);
        } else {
            throw new EventIDNotFound();
        }
    }

    public void suggest(String player, String game) throws UsernameNotFound, GameNotFoundException, ThisGameHasAlreadyBeenSuggested {
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
