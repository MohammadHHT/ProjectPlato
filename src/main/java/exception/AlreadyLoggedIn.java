package exception;

public class AlreadyLoggedIn extends Exception {
    public AlreadyLoggedIn() {
        super("You've already logged in!");
    }
}
