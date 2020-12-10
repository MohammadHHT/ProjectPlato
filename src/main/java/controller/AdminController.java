package controller;

import exception.*;
import model.*;

import java.time.LocalDate;

public class AdminController {
    private static final AdminController adminController = new AdminController();

    private AdminController() {
    }

    public static AdminController getAdminController() {
        return adminController;
    }

    public void addEvent(String game, int syear, int smonth, int sday, int fyear, int fmonth, int fday, long score) throws GameNotFound {
        if (game.equalsIgnoreCase("BattleSea") || game.equalsIgnoreCase("DotsAndBoxes")) {
            new Event(game, LocalDate.of(syear, smonth, sday), LocalDate.of(fyear, fmonth, fday), score);
        } else {
            throw new GameNotFound();
        }
    }

    public String showEvents() {
        StringBuilder tmp = new StringBuilder();
        for (Event e : Event.getEvents().values()) {
            tmp.append("ID: ").append(e.getEventID()).append('\n').append("Game: ").append(e.getGameName()).append('\n')
                    .append("Start: ").append(e.getStart().getYear()).append('-').append(e.getStart().getMonth()).append('-').append(e.getStart().getDayOfMonth()).append('\n')
                    .append("Finish: ").append(e.getEnd().getYear()).append('-').append(e.getEnd().getMonth()).append('-').append(e.getEnd().getDayOfMonth()).append('\n')
                    .append("Score: ").append(e.getScore()).append("\n\n");
        }
        return tmp.toString().trim();
    }

    public void editEvent(long eventID, int score) throws EventIDNotFound {
        Event event = Event.getEvents().get(eventID);
        if (event != null) {
            event.setScore(score);
        } else {
            throw new EventIDNotFound();
        }
    }

    public void editEvent(long eventID, String field, int year, int month, int day) throws EventIDNotFound {
        Event event = Event.getEvents().get(eventID);
        if (event != null) {
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

    public void addSuggestion(String username, String game) throws UsernameNotFound, GameNotFound, GameAlreadySuggested {
        Player player = Player.getPlayers().get(username);
        if (player != null) {
            if (game.equalsIgnoreCase("BattleSea") || game.equalsIgnoreCase("DotsAndBoxes")) {
                for (Long l : player.getSuggestions()) {
                    if (Suggestion.getSuggestions().get(l).getGame().equals(game)) {
                        throw new GameAlreadySuggested();
                    }
                }
                new Suggestion(player, game);
            } else {
                throw new GameNotFound();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public String showSuggestions() {
        StringBuilder tmp = new StringBuilder();
        for (Suggestion s : Suggestion.getSuggestions().values()) {
            tmp.append("ID: ").append(s.getSuggestionID()).append('\n').append("Player: ").append(s.getPlayer()).append('\n').append("Game: ").append(s.getGame()).append("\n\n");
        }
        return tmp.toString().trim();
    }

    public void removeSuggestion(long suggestionID) throws SuggestionIDNotFound {
        Suggestion suggestion = Suggestion.getSuggestions().get(suggestionID);
        if (suggestion != null) {
            Player.getPlayers().get(suggestion.getPlayer()).getSuggestions().remove(suggestionID);
            Suggestion.getSuggestions().remove(suggestionID);
        } else
            throw new SuggestionIDNotFound();
    }

    public String showUsers() {
        StringBuilder tmp = new StringBuilder();
        for (User u : User.getUsers().values()) {
            tmp.append(u.getFirstName()).append(' ').append(u.getLastName()).append(": ").append(u.getUsername()).append('\n');
        }
        return tmp.toString().trim();
    }

    public String showUserProfile(String username) throws UsernameNotFound {
        User user = User.getUsers().get(username);
        if (user != null) {
            return user.toString();
        } else {
            throw new UsernameNotFound();
        }
    }
}
