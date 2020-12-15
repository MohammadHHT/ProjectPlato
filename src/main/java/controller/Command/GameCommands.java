package controller.Command;

import controller.GameController;
import view.DotsAndBoxesMenu;

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
            case "DotsAndBoxes":
                DotsAndBoxesCommand.getDotsAndBoxesCommand().execute(tokens);
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

    private static class DotsAndBoxesCommand implements ExecuteCommand {
        private static final DotsAndBoxesCommand dotsAndBoxesCommand = new DotsAndBoxesCommand();

        private DotsAndBoxesCommand() {
        }

        static DotsAndBoxesCommand getDotsAndBoxesCommand() {
            return dotsAndBoxesCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[2]) {
                case "occupy":                                                      // when we want to occupy a line (edge). we must pass x1,y1,x2,y2 through menu
                    done(GameController.getGameController().names());
                    break;
                case "open":
                    done(GameController.getGameController().open(tokens[2], tokens[3]));
                    break;
            }
        }
    }
}
