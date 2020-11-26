package controller.Command;

public class Command implements ResolveCommand {

    private static final Command command = new Command();

    private Command() {}

    public static Command getCommand() {
        return command;
    }

    //Determines command is related to users commands or games commands ...
    @Override
    public void resolveCommand(String[] tokens) {
        if (tokens[0].equals("User")) {
            UserCommands.getUserCommands().resolveCommand(tokens);
        }
    }
}
