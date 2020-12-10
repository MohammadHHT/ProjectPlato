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

    public void addEvent(String game, int syear, int smonth, int sday, int fyear, int fmonth, int fday, long score) throws InvalidGameName {
        if (game.equalsIgnoreCase("BattleSea") || game.equalsIgnoreCase("DotsAndBoxes")) {
            new Event(game, LocalDate.of(syear, smonth, sday), LocalDate.of(fyear, fmonth, fday), score);
        } else {
            throw new InvalidGameName();
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

    public void suggest(String player, String game) throws UsernameNotFound, GameNotFoundException, GameAlreadySuggested {
        if (Player.getPlayers().containsKey(player)) {
            if (game.equals("BattleSea") || game.equals("DotsAndBoxes")) {
                for (Long l : Player.getPlayers().get(player).getSuggestions()) {
                    if (Suggestion.getSuggestions().get(l).getGame().equals(game)) {
                        throw new GameAlreadySuggested();
                    }
                }
                new Suggestion(player, game);
            } else {
                throw new GameNotFoundException();
            }
        } else {
            throw new UsernameNotFound();
        }
    }

    public String showSuggestions() {
        StringBuilder tmp = new StringBuilder();
        for (Suggestion s : Suggestion.getSuggestions().values()) {
            tmp.append(Player.getPlayers().get(s.getPlayer()).getUsername()).append(" ").append(s.getGame()).append("\n");
        }
        return tmp.toString().trim();
    }

    public void removeSuggestion(long sugID) throws SuggestionIDNotFound {
        if (Suggestion.getSuggestions().containsKey(sugID)) {
            Player.getPlayers().get(Suggestion.getSuggestions().get(sugID).getPlayer()).getSuggestions().remove(sugID);
            Suggestion.getSuggestions().remove(sugID);
        } else
            throw new SuggestionIDNotFound();
    }

    public String showUsers() {
        StringBuilder tmp = new StringBuilder();
        for (User u : User.getUsers().values()) {
            tmp.append(u.getUsername()).append(" ");
        }
        return tmp.toString().trim();
    }

    public String showUserProfile(String username) throws UsernameNotFound {
        if (Player.getPlayers().containsKey(username)) {
            Player player = Player.getPlayers().get(username);
            StringBuilder tmp = new StringBuilder();
            tmp.append(player.getFirstName()).append(" ").append(player.getLastName()).append("\n").append(player.getUsername()).append(" ").append(player.getPassword()).append("\n").append(player.getEmail()).append(" ").append(player.getPhone()).append("\n").append(player.getScore()).append("\n");
            for (String s : player.getFavoriteGames()) {
                tmp.append(s).append(" ");
            }
            return tmp.toString().trim();
        } else {
            throw new UsernameNotFound();
        }
    }
}
