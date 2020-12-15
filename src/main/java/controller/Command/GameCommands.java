package controller.Command;

import controller.GameController;

public class GameCommands implements ResolveCommand {
    private static final GameCommands gameCommands = new GameCommands();

    private GameCommands() {
    }

    static GameCommands getGameCommands() {
        return gameCommands;
    }

    @Override
    public void resolveCommand(String[] tokens) throws Exception {
        switch (tokens[1]) {
            case "names":
            case "open":
                GameCommand.getGameCommand().execute(tokens);
                break;
        }
    }

    private static class GameCommand implements ExecuteCommand {
        private static final GameCommand gameCommand = new GameCommand();

        private GameCommand() {
        }

        static GameCommand getGameCommand() {
            return gameCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[1]) {
                case "names":
                    done(GameController.getGameController().names());
                    break;
                case "open":
                    done(GameController.getGameController().open(tokens[2], tokens[3]));
                    break;
            }
        }
    }
}
