package controller.Command;

import controller.AccountManager;
import controller.PlayerController;
import model.Admin;
import model.Player;

public class UserCommands implements ResolveCommand {
    private static final UserCommands userCommands = new UserCommands();

    private UserCommands() {}

    static UserCommands getUserCommands() {
        return userCommands;
    }

    @Override
    public void resolveCommand(String[] tokens) throws Exception {
        if (tokens[1].equals("register") || tokens[1].equals("delete")) {
            RegisterCommand.getRegisterCommand().execute(tokens);
        } else if (tokens[1].equals("login") || tokens[1].equals("logout")) {
            LoginCommand.getLoginCommand().execute(tokens);
        } else if (tokens[1].equals("showPoint") || tokens[1].equals("showFavoriteGames") || tokens[1].equals("showAdminMessages") || tokens[1].equals("showAdminSuggestions") ||
                tokens[1].equals("playSuggested") || tokens[1].equals("showLastGame") || tokens[1].equals("addFriend")) {
            PlayerCommand.getPlayerCommand().execute(tokens);
        }
    }

    //RegisterCommand nested class
    private static class RegisterCommand implements ExecuteCommand {
        private static final RegisterCommand registerCommand = new RegisterCommand();

        private RegisterCommand() {}

        static RegisterCommand getRegisterCommand() {
            return registerCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "register":
                    AccountManager.getAccountManager().register(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
                    if (AccountManager.getAccountManager().getLoggedInUser() instanceof Admin) {
                        done("Admin logged in");
                    } else {
                        done("Player logged in");
                    }
                    break;
                case "delete":
                    AccountManager.getAccountManager().deleteAccount(tokens[2], tokens[3]);
                    done("Account deleted");
                    break;
            }
        }
    }

    //LoginCommand nested class
    private static class LoginCommand implements ExecuteCommand {
        private static final LoginCommand loginCommand = new LoginCommand();

        private LoginCommand() {}

        static LoginCommand getLoginCommand() {
            return loginCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "login":
                    AccountManager.getAccountManager().login(tokens[2], tokens[3]);
                    if (AccountManager.getAccountManager().getLoggedInUser() instanceof Admin) {
                        done("Admin logged in");
                    } else {
                        done("Player logged in");
                    }
                    break;
                case "logout":
                    AccountManager.getAccountManager().logout();
                    done("Logged out");
                    break;
            }
        }
    }

    //PlayerCommand nested class
    private static class PlayerCommand implements ExecuteCommand {
        private static final PlayerCommand playerCommand = new PlayerCommand();

        private PlayerCommand() {}

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
                    done("Game begins");
                    break;
                case "showLastGame":
                    PlayerController.getPlayerController().showLastGame(tokens[2]);
                    break;
                case "addFriend":

                    break;
            }
        }
    }

    //AdminCommand nested class
    private static class AdminCommand implements ExecuteCommand {
        private static final AdminCommand adminCommand = new AdminCommand();

        private AdminCommand() {}

        static AdminCommand getAdminCommand() {
            return adminCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "addEvent":

                    break;
                case "showEvents":

                    break;
                case "editEvent":

                    break;
                case "removeEvent":

                    break;
                case "suggest":

                    break;
                case "showSuggestions":

                    break;
                case "removeSuggestion":

                    break;
                case "showUsers":

                    break;
                case "showUserProfile":

                    break;
            }
        }
    }
}