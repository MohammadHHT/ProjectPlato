package controller.Command;

import controller.AccountController;
import controller.AdminController;
import controller.FriendController;
import controller.PlayerController;
import model.Admin;

public class UserCommands implements ResolveCommand {
    private static final UserCommands userCommands = new UserCommands();

    private UserCommands() {
    }

    static UserCommands getUserCommands() {
        return userCommands;
    }

    @Override
    public void resolveCommand(String[] tokens) throws Exception {
        switch (tokens[1]) {
            case "register":
            case "delete":
            case "login":
                RegisterCommand.getRegisterCommand().execute(tokens);
                break;
            case "showPoint":
            case "showFavoriteGames":
            case "showAdminMessages":
            case "showAdminSuggestions":
            case "playSuggested":
            case "showLastGame":
            case "addFriend":
                PlayerCommand.getPlayerCommand().execute(tokens);
                break;
            case "addEvent":
            case "showEvents":
            case "editEvent":
            case "removeEvent":
            case "suggest":
            case "showSuggestions":
            case "removeSuggestion":
            case "showUsers":
            case "showUserProfile":
                AdminCommand.getAdminCommand().execute(tokens);
                break;
            case "showInfo":
            case "changePassword":
            case "editField":
            case "showPlatoStatistics":
            case "showHistory":
            case "showGameStatistics":
                AccountCommand.getAccountCommand().execute(tokens);
                break;
            case "showFriends":
            case "removeFriend":
            case "showRequests":
            case "accept":
            case "decline":
                FriendCommand.getFriendCommand().execute(tokens);
                break;
        }
    }

    //RegisterCommand nested class
    private static class RegisterCommand implements ExecuteCommand {
        private static final RegisterCommand registerCommand = new RegisterCommand();

        private RegisterCommand() {
        }

        static RegisterCommand getRegisterCommand() {
            return registerCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "register":
                    done(AccountController.getAccountController().register(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
                    break;
                case "delete":
                    AccountController.getAccountController().delete(tokens[2], tokens[3]);
                    done("Deleted");
                    break;
                case "login":
                    done(AccountController.getAccountController().login(tokens[2], tokens[3]));
                    break;
            }
        }
    }

    //AccountCommand nested class
    private static class AccountCommand implements ExecuteCommand {
        private static final AccountCommand accountCommand = new AccountCommand();

        private AccountCommand() {
        }

        static AccountCommand getAccountCommand() {
            return accountCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "showInfo":
                    done(AccountController.getAccountController().showInfo(tokens[2]));
                    break;
                case "changePassword":
                    AccountController.getAccountController().changePassword(tokens[2], tokens[3], tokens[4]);
                    done("Changed");
                    break;
                case "editField":
                    AccountController.getAccountController().editField(tokens[2], tokens[3], tokens[4]);
                    done("Edited");
                    break;
                case "showPlatoStatistics":
                    done(AccountController.getAccountController().showPlatoStatistics(tokens[2]));
                    break;
                case "showHistory":
                    done(AccountController.getAccountController().showHistory(tokens[2]));
                    break;
                case "showGameStatistics":
                    done(AccountController.getAccountController().showGameStatistics(tokens[2], tokens[3]));
                    break;
            }
        }
    }

    //PlayerCommand nested class
    private static class PlayerCommand implements ExecuteCommand {
        private static final PlayerCommand playerCommand = new PlayerCommand();

        private PlayerCommand() {
        }

        static PlayerCommand getPlayerCommand() {
            return playerCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "showPoint":
                    done(PlayerController.getPlayerController().showPoints(tokens[2]));
                    break;
                case "showFavoriteGames":
                    done(PlayerController.getPlayerController().showFavoriteGames(tokens[2]));
                    break;
                case "showAdminMessages":
                    done(PlayerController.getPlayerController().showAdminMessages(tokens[2]));
                    break;
                case "showAdminSuggestions":
                    done(PlayerController.getPlayerController().showAdminSuggestions(tokens[2]));
                    break;
                case "playSuggested":
                    PlayerController.getPlayerController().playSuggested(tokens[2], tokens[3]);
                    done("Began");
                    break;
                case "showLastGame":
                    done(PlayerController.getPlayerController().showLastGame(tokens[2]));
                    break;
                case "addFriend":
                    PlayerController.getPlayerController().addFriend(tokens[2], tokens[3]);
                    done("Requested");
                    break;
            }
        }
    }

    //AdminCommand nested class
    private static class AdminCommand implements ExecuteCommand {
        private static final AdminCommand adminCommand = new AdminCommand();

        private AdminCommand() {
        }

        static AdminCommand getAdminCommand() {
            return adminCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "addEvent":
                    AdminController.getAdminController().addEvent(tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]), Long.parseLong(tokens[9]));
                    done("Added");
                    break;
                case "showEvents":
                    done(AdminController.getAdminController().showEvents());
                    break;
                case "editEvent":
                    if (tokens.length == 7) {
                        AdminController.getAdminController().editEvent(Long.parseLong(tokens[2]), tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                    } else {
                        AdminController.getAdminController().editEvent(Long.parseLong(tokens[2]), Integer.parseInt(tokens[3]));
                    }
                    done("Edited");
                    break;
                case "removeEvent":
                    AdminController.getAdminController().removeEvent(Long.parseLong(tokens[2]));
                    done("Removed");
                    break;
                case "suggest":
                    AdminController.getAdminController().addSuggestion(tokens[2], tokens[3]);
                    done("Suggested");
                    break;
                case "showSuggestions":
                    done(AdminController.getAdminController().showSuggestions());
                    break;
                case "removeSuggestion":
                    AdminController.getAdminController().removeSuggestion(Long.parseLong(tokens[2]));
                    done("Removed");
                    break;
                case "showUsers":
                    done(AdminController.getAdminController().showUsers());
                    break;
                case "showUserProfile":
                    done(AdminController.getAdminController().showUserProfile(tokens[2]));
                    break;
            }
        }
    }

    //FriendCommand nested class
    private static class FriendCommand implements ExecuteCommand {
        private static final FriendCommand friendCommand = new FriendCommand();

        private FriendCommand() {
        }

        static FriendCommand getFriendCommand() {
            return friendCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "showFriends":
                    done(FriendController.getFriendController().showFriends(tokens[2]));
                    break;
                case "removeFriend":
                    FriendController.getFriendController().removeFriend(tokens[2], tokens[3]);
                    done("Removed");
                    break;
                case "showRequests":
                    done(FriendController.getFriendController().showRequests(tokens[2]));
                    break;
                case "accept":
                    FriendController.getFriendController().accept(tokens[2], tokens[3]);
                    done("Accepted");
                    break;
                case "decline":
                    FriendController.getFriendController().decline(tokens[2], tokens[3]);
                    done("Declined");
                    break;
            }
        }
    }
}