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
            switch (tokens[0]) {
                case "user":
                    UserCommands.getUserCommands().resolveCommand(tokens);
                    break;
                case "game":
                    GameCommands.getGameCommands().resolveCommand(tokens);
                    break;
            }
        } catch (Exception e) {
            Server.getServer().send("Exception " + e.getMessage());
        }
    }
}
