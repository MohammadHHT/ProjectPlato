package exception;

public class EventIDNotFound extends Exception {
    public EventIDNotFound() {
        super("There is no event with this ID!");
    }
}
