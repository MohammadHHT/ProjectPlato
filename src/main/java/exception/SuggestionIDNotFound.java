package exception;

public class SuggestionIDNotFound extends Exception {
    public SuggestionIDNotFound() {
        super("There is no suggestion with this ID!");
    }
}
