package controller.Command;

import controller.FriendController;

public class GameCommands implements ResolveCommand {

    private static final GameCommands gameCommands = new GameCommands();

    public static GameCommands getGameCommands() {
        return gameCommands;
    }

    @Override
    public void resolveCommand(String[] tokens) throws Exception {

    }

    //FriendCommand nested class
    private static class DotsAndBoxesCommand implements ExecuteCommand {
        private static final GameCommands.DotsAndBoxesCommand dotsAndBoxesCommand = new GameCommands.DotsAndBoxesCommand();

        private DotsAndBoxesCommand() {
        }

        static DotsAndBoxesCommand getDotsAndBoxesCommand() {
            return dotsAndBoxesCommand;
        }

        @Override
        public void execute(String[] tokens) throws Exception {
            switch (tokens[2]) {
                case "end of my turn":
                case "show score":
                case "show available lines":
                case "show table":
                case "who is next?":
                case "show result":
                case "end" :
                case "draw" :


            }
        }
    }

}
