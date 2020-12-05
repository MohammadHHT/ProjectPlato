package controller.Command;

import controller.AccountManager;
import model.Admin;
import model.Player;

public class UserCommands implements ResolveCommand {
    private static final UserCommands userCommands = new UserCommands();

    private UserCommands() {}

    static UserCommands getUserCommands() {
        return userCommands;
    }

    // user commands can be :register, login, delete, 
    // showFriends, removeFriend, viewFriendProfile, addFriend, showFriendRequests, acceptFriend, declineFriend

    @Override
    public void resolveCommand(String[] tokens) throws Exception {
        if (tokens[1].equals("register")) {
            RegisterCommand.getRegisterCommand().execute(tokens);
        } else if (tokens[1].equals("login") || tokens[1].equals("logout")) {
            LoginCommand.getLoginCommand().execute(tokens);
        } else if (tokens[1].equals("delete")) {
            DeleteCommand.getDeleteCommand().execute(tokens);
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
            AccountManager.getAccountManager().register(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]);
            if (AccountManager.getAccountManager().getLoggedInUser() instanceof Admin) {
                done("Admin logged in");
            } else {
                done("Player logged in");
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
            if (tokens[1].equals("login")) {
                AccountManager.getAccountManager().login(tokens[2], tokens[3]);
                if (AccountManager.getAccountManager().getLoggedInUser() instanceof Admin) {
                    done("Admin logged in");
                } else {
                    done("Player logged in");
                }
            } else {
                AccountManager.getAccountManager().logout();
                done("logged out");
            }
        }
    }

    //DeleteCommand nested class
    private static class DeleteCommand implements ExecuteCommand {
        private static final DeleteCommand deleteCommand = new DeleteCommand();

        private DeleteCommand() {}

        static DeleteCommand getDeleteCommand() {
            return deleteCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            AccountManager.getAccountManager().deleteAccount(tokens[2], tokens[3]);
            done("Account deleted");
        }
    }
}