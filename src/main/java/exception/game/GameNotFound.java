package exception.game;

public class GameNotFound extends Exception {
    public GameNotFound() {
        super("There is no game with this name!");
    }
}
