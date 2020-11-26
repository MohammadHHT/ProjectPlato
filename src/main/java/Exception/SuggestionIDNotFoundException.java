package Exception;

public class SuggestionIDNotFoundException extends Exception{
    public SuggestionIDNotFoundException() {
        super("There is no suggestion with this ID!");
    }
}
