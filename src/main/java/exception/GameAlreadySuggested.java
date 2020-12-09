package exception;

public class GameAlreadySuggested extends Exception {
    public GameAlreadySuggested() {
        super("This Game Has Already Been Suggested!");
    }
}
