package exception;

public class InvalidGameNameException extends Exception {
    public InvalidGameNameException() {
        super("Invalid Game Name!");
    }
}
