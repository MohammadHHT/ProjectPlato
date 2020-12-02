package controller.Command;

public class UserCommands implements ResolveCommand {
    private static final UserCommands userCommands = new UserCommands();

    private UserCommands() {}

    static UserCommands getUserCommands() {
        return userCommands;
    }

    // user commands can be :register, login, delete, 
    // showFriends, removeFriend, viewFriendProfile, addFriend, showFriendRequests, acceptFriend, declineFriend

    @Override
    public void resolveCommand(String[] tokens) {
        if (tokens[1].equals("register")) {
            RegisterCommand.getRegisterCommand().execute(tokens);
        } else if (tokens[1].equals("login")) {
            LoginCommand.getLoginCommand().execute(tokens);
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
        public void execute(String[] tokens) {

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
        public void execute(String[] tokens) {

        }
    }
}