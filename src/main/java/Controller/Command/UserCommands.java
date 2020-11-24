package Controller.Command;

public class UserCommands implements ResolveCommand {
    private static final UserCommands userCommands = new UserCommands();

    private UserCommands() {}

    public static UserCommands getUserCommands() {
        return userCommands;
    }

    @Override
    public void resolveCommand(String[] tokens) {

    }

    class RegisterCommand {

    }

    class LoginCommand {

    }
}