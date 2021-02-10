package main.back.controller;

import main.back.account.*;
import main.back.game.Event;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface UserCommand {
    static String resolve(String[] tokens) {
        switch (tokens[0]) {
            case "register":
                if (tokens.length == 3) {
                    return register(tokens[1], tokens[2]);
                } else {
                    return register(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                }
            case "edit":
                return edit(tokens[1], tokens[2], tokens[3], tokens[4]);
            case "login":
                return login(tokens[1], tokens[2]);
            case "search":
                return search(tokens[1], tokens[2], tokens[3]);
            case "suggestions":
                return suggestions(tokens[1], tokens[2]);
            case "events":
                return events(tokens[1], tokens[2]);
            case "addevent":
                return addEvent(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]), Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]));
            case "deleteevent":
                return deleteEvent(tokens[1], tokens[2], Long.parseLong(tokens[3]));
            case "joinevent":
                return joinEvent(tokens[1], tokens[2], Long.parseLong(tokens[3]));
            case "users":
                return users(tokens[1], tokens[2]);
            case "friends":
                return friends(tokens[1], tokens[2]);
            case "isfriend" :
                return isFriend(tokens[1], tokens[2], tokens[3]);
            case "allUsers":
                return allUsers(tokens[1], tokens[2]);
            case "sendSuggestion":
                return addSuggestion(tokens[1], tokens[2], tokens[3], tokens[4]);
            case "loadSuggestedGame":
                return loadSuggestedGame(tokens[1], tokens[2]);
            case "loadFriendsRequest":
                return loadFriendsRequest(tokens[1], tokens[2]);
            case "acceptFriend":
                return acceptFriend(tokens[1], tokens[2], tokens[3]);
            case "declineFriend":
                return declineFriend(tokens[1], tokens[2], tokens[3]);
            case "loadChatHistory":
                return loadChatHistory(tokens[1], tokens[2], tokens[3]);
            case "sendMessage":
                if (tokens.length > 5) {
                    String msg = "";
                    for (int i = 4; i < tokens.length; i++) {
                        msg = msg.concat(tokens[i]).concat(" ");
                    }
                    return sendMessage(tokens[1], tokens[2], tokens[3], msg);
                }
                return sendMessage(tokens[1], tokens[2], tokens[3], tokens[4]);
            case "loadPlatoBotMessage":
                return loadPlatoBotMessage(tokens[1], tokens[2]);
            default:
                return "failed command";
        }
    }

    static String register(String field, String value) {
        switch (field) {
            case "username":
                return User.getUsers().containsKey(value) ? "false" : "true";
            case "phone":
                for (User u : User.getUsers().values()) {
                    if (u.getPhone().equalsIgnoreCase(value)) {
                        return "false";
                    }
                }
                return "true";
            case "email":
                for (User u : User.getUsers().values()) {
                    if (u.getEmail().equalsIgnoreCase(value)) {
                        return "false";
                    }
                }
                return "true";
            default:
                return "true";
        }
    }

    static String register(String firstName, String lastName, String username, String password, String email, String phone) {
        Player player = new Player(firstName, lastName, username, password, email, phone);
        player.login();
        return player.getToken();
    }

    static String edit(String username, String token, String field, String value) {
        User user = User.getUsers().get(username);
        if (user.getToken().equals(token)) {
            switch (field) {
                case "firstname":
                    user.setFirstName(value);
                    return "true";
                case "lastname":
                    user.setLastName(value);
                    return "true";
                case "username":
                    if (User.getUsers().containsKey(value)) {
                        return "false username";
                    }
                    user.setUsername(value);
                    return "true";
                case "email":
                    for (User u : User.getUsers().values()) {
                        if (u.getUsername().equalsIgnoreCase(value)) {
                            return "false email";
                        }
                    }
                    user.setEmail(value);
                    return "true";
                    default:
                        return "true";
            }
        }
        return "true";
    }

    static String login(String username, String password) {
        User user = User.getUsers().get(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                if (!user.isLogged()) {
                    user.login();
                    if (user instanceof Admin) {
                        return "admin " + user.getUsername() + " " + user.getToken() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth();
                    } else {
                        return "player " + user.getUsername() + " " + user.getToken() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPhone() + " " + user.getEmail() + " " +
                                user.getDate().getYear() + " " + user.getDate().getMonthValue() + " " + user.getDate().getDayOfMonth() + " " + ((Player) user).getLevel() + " " + ((Player) user).getMoney();
                    }
                } else {
                    return "failed logging";
                }
            } else {
                return "failed password";
            }
        } else {
            return "failed username";
        }
    }

    static String search(String username, String token, String input) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder users = new StringBuilder();
            for (String s : User.getUsers().keySet()) {
                if (s.contains(input)) {
                    users.append(s).append(" ");
                }
            }
            return users.toString().trim();
        }
        return null;
    }

    static String suggestions(String username, String token) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {
            String suggestions = "";
            for (Long l : player.getSuggestions()) {
                suggestions += Suggestion.getSuggestions().get(l).getGame();
            }
            return suggestions;
        }
        return null;
    }

    static String events(String username, String token) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder events = new StringBuilder();
            for (Event e : Event.getEvents().values()) {
                if (e.getEnd().isAfter(LocalDateTime.now())) {
                    events.append(e.toString()).append("/");
                }
            }
            return events.toString().substring(0, events.toString().length() - 1);
        }
        return null;
    }

    static String joinEvent(String username, String token, long eventID) {
        if (Player.getPlayers().get(username).getToken().equals(token)) {
            Event.getEvents().get(eventID).join(username);
        }
        return null;
    }

    static String addEvent(String username, String token, String game, int prize, int sday, int smonth, int syear, int fday, int fmonth, int fyear) {
        if (Admin.getAdmins().get(username).getToken().equals(token)) {
            Event event = new Event(game, LocalDateTime.of(syear, smonth, sday, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()), LocalDateTime.of(fyear, fmonth, fday, LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()), prize);
            return String.valueOf(event.getEventID() + " " + event.getStart().getMonth() + " " + event.getEnd().getMonth());
        }
        return null;
    }

    static String deleteEvent(String username, String token, long eventID) {
        if (Admin.getAdmins().get(username).getToken().equals(token)) {
            Event.deleteEvent(eventID);
            return null;
        }
        return null;
    }

    static String users(String username, String token) {
        if (User.getUsers().get(username).getToken().equals(token)) {
            StringBuilder users = new StringBuilder();
            for (String s : User.getUsers().keySet()) {
                users.append(s).append(" ");
            }
            return users.toString().trim();
        }
        return null;
    }

    static String friends(String username, String token) {
        Player player = Player.getPlayers().get(username);
        if (player.getToken().equals(token)) {
            StringBuilder friends = new StringBuilder();
            for (String s : player.getFriends()) {
                friends.append(s).append(" ");
            }
            return friends.toString().trim();
        }
        return null;
    }

    static String isFriend(String username, String token, String oppUsername) {
        if (User.getUsers().get(username).getToken().equals(token)){
            if (Player.getPlayers().get(username).getFriends().contains(oppUsername)) {
                return "yes";
            } else return "no";
        }
        return "token invalid";
    }

    static String allUsers(String username, String token) {
        User user = User.getUsers().get(username);
        String usersInfo = null;
        if (user.getToken().equals(token)) {
            for (Map.Entry<String, User> entry : User.getUsers().entrySet()) {
                usersInfo += entry.getValue().toString() + ("/");
            }
            return usersInfo;
//            return "amin lotfi aminlotfi 123456 amin@gmail.com 09304087303/mahdi hadi mahdihadiam 567890 mahdi@gmail.com 09126086363/mehran khaksar mehrankhaksar 654321 mehran@gmail.com 09122243286";
        } else {
            return null;
        }
    }

    static String addSuggestion(String username, String token, String playerUsername, String game) {
        User user = User.getUsers().get(username);
        Player player = Player.getPlayers().get(playerUsername);
        if (user.getToken().equals(token)) {
            new Suggestion(player, game);
            System.out.println(playerUsername + " added.");
            return "send successfully";
        } else {
            return "send suggestions unsuccessfully";
        }
    }

    static String loadSuggestedGame(String username, String token) {
        boolean isSeaBattle = false;
        boolean isDotsAndBoxes = false;
        Set<Long> suggestions = Player.getPlayers().get(username).getSuggestions();
        User user = User.getUsers().get(username);

        if (user.getToken().equals(token)) {
            for (Long suggestionID : suggestions) {
                if (Suggestion.getSuggestions().containsKey(suggestionID)) {
                    if (Suggestion.getSuggestions().get(suggestionID).getGame().equals("BattleSea")) {
                        isSeaBattle = true;
                    } else if (Suggestion.getSuggestions().get(suggestionID).getGame().equals("DotsAndBoxes")) {
                        isDotsAndBoxes = true;
                    }
                } else {
                    return "fail No game suggested.";
                }
            }
            if (isSeaBattle && isDotsAndBoxes) {
                return "Battle Sea Dots And Boxes";
            } else if (isSeaBattle) {
                return "Battle Sea";
            } else if (isDotsAndBoxes) {
                return "Dots And Boxes";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    static String loadFriendsRequest(String username, String token) {
        User user = User.getUsers().get(username);
        Set<String> friendsRequestUsername = Player.getPlayers().get(username).getFriendRequest();
        String allUsernames = null;
        if (user.getToken().equals(token)) {
            for (String friendUsername : friendsRequestUsername) {
                allUsernames += (Player.getPlayers().get(friendUsername).getUsername()) + (" ");
            }
            return allUsernames;
        } else {
            return null;
        }
    }

    static String acceptFriend(String username, String token, String friendUsername) {
        User user = User.getUsers().get(username);
        Player player = Player.getPlayers().get(username);
        if (user.getToken().equals(token)) {
            if (player.acceptFriendRequest(friendUsername)) {
                return "accept successfully";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    static String declineFriend(String username, String token, String friendUsername) {
        User user = User.getUsers().get(username);
        Player player = Player.getPlayers().get(username);
        if (user.getToken().equals(token)) {
            if (player.declineFriendRequest(friendUsername)) {
                return "decline successfully";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    static String loadChatHistory(String username, String token, String PlayerUsername) {
        User user = User.getUsers().get(username);
        Player player = Player.getPlayers().get(PlayerUsername);
        String allMessages = null;
        if (user.getToken().equals(token)) {
            for (Long messageID : player.getMessages()) {
                allMessages += Message.getMessages().get(messageID).getMessage() + "@" +
                        Message.getMessages().get(messageID).getDate().getHour() + ":" +
                        Message.getMessages().get(messageID).getDate().getMinute() + "/" +
                        Message.getMessages().get(messageID).getDate().getMonthValue() + "/" +
                        Message.getMessages().get(messageID).getDate().getDayOfMonth();
            }
            return allMessages;
        } else {
            return null;
        }
    }

    static String sendMessage(String username, String token, String playerUsername, String messageContent) {
        User user = User.getUsers().get(username);
        if (user.getToken().equals(token)) {
            Message message = new Message(playerUsername, messageContent);
            return messageContent + "/" + message.getDate().getHour() + ":" + message.getDate().getMinute() + "/" +
                    message.getDate().getMonthValue() + "/" + message.getDate().getDayOfMonth();
        } else {
            return null;
        }
    }

    static String loadPlatoBotMessage(String username, String token) {
        Player player = Player.getPlayers().get(username);
        StringBuilder allMessages = new StringBuilder();
        if (player.getToken().equals(token)) {
            for (Long messageID : player.getMessages()) {
                allMessages.append(Message.getMessages().get(messageID).getMessage()).append("@").append(Message.getMessages().get(messageID).getDate().getHour()).append(":").append(Message.getMessages().get(messageID).getDate().getMinute()).append("/").append(Message.getMessages().get(messageID).getDate().getMonthValue()).append("/").append(Message.getMessages().get(messageID).getDate().getDayOfMonth()).append("-");
            }
            allMessages.delete(allMessages.length()-1, allMessages.length());
            return allMessages.toString();
        } else {
            return null;
        }
    }
}
