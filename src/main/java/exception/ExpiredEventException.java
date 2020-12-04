package exception;

public class ExpiredEventException extends Exception {
    public ExpiredEventException() {
        super("This event is expired.");
    }
}
