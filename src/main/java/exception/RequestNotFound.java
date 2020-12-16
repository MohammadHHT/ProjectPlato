package exception;

public class RequestNotFound extends Exception {
    public RequestNotFound() {
        super("There is no request from this user!");
    }
}
