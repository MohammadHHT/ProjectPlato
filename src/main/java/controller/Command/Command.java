package controller.Command;

import controller.Server;

public class Command implements ResolveCommand {

    private static final Command command = new Command();

    private Command() {}

    public static Command getCommand() {
        return command;
    }

    @Override
    public void resolveCommand(String[] tokens) {
        try {
            if (tokens[0].equals("User")) {
                UserCommands.getUserCommands().resolveCommand(tokens);
            }
        } catch (Exception e) {
            Server.getServer().send("Exception " + e.getMessage());
        }
    }
}
