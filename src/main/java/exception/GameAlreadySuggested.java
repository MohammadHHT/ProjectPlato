package exception;

public class GameAlreadySuggested extends Exception {
    public GameAlreadySuggested() {
        super("This game has already been suggested!");
    }
}
