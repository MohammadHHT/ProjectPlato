package exception;

public class InvalidGameName extends Exception {
    public InvalidGameName() {
        super("Invalid game name!");
    }
}
