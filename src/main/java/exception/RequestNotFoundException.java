package exception;

public class RequestNotFoundException extends Exception {
    public RequestNotFoundException() {
        super("There is no request from this user!");
    }
}
