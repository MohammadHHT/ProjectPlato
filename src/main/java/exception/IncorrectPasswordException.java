package exception;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Password is incorrect!");
    }
}
