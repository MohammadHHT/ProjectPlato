package exception;

public class ThisGameHasAlreadyBeenSuggested extends Exception {
    public ThisGameHasAlreadyBeenSuggested() {
        super("This Game Has Already Been Suggested!");
    }
}
