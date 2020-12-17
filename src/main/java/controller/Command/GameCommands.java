package controller.Command;

import controller.Command.game.DotsAndBoxesController;
import controller.Command.game.GameController;

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
                break;
            case "BattleSea":
                BattleSeaCommand.getBattleSeaCommand().execute(tokens);
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
                case "turn":
                    done(GameController.getGameController().turn(Long.parseLong(tokens[2])));
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
                case "occupy":                                                      // when we want to occupy a line (edge). we must pass x1,y1,x2,y2 through menu (there is no username coming from menu)
                    done(DotsAndBoxesController.getDotsAndBoxesController().occupy(Long.parseLong(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), null));
                    break;
                case "open":
                    done(GameController.getGameController().open(tokens[3], tokens[1]));
                    break;
                case "endofmyturn":
                    done(DotsAndBoxesController.getDotsAndBoxesController().endOfMyTurn(Long.parseLong(tokens[3])));
                    break;
                case "showscore":
                    done(DotsAndBoxesController.getDotsAndBoxesController().showScore(Long.parseLong(tokens[3])));
                    break;
                case "showavailablelines":
                    done(DotsAndBoxesController.getDotsAndBoxesController().showAvailableLines(Long.parseLong(tokens[3])));
                    break;
                case "showtable":
                    done(DotsAndBoxesController.getDotsAndBoxesController().showTable(Long.parseLong(tokens[3])));
                    break;
                case "whoisnext?":
                    done(DotsAndBoxesController.getDotsAndBoxesController().whoIsNext(Long.parseLong(tokens[3])));
                    break;
                case "showresult":
                    done(DotsAndBoxesController.getDotsAndBoxesController().showResult(Long.parseLong(tokens[3])));
                    break;
                case "end":
                    done(DotsAndBoxesController.getDotsAndBoxesController().end(Long.parseLong(tokens[3])));
            }
        }
    }

    private static class BattleSeaCommand implements ExecuteCommand {
        private static final BattleSeaCommand battleSeaCommand = new BattleSeaCommand();

        private BattleSeaCommand() {
        }

        static BattleSeaCommand getBattleSeaCommand() {
            return battleSeaCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[2]) {
                //TODO add commands.
            }
        }
    }
}
