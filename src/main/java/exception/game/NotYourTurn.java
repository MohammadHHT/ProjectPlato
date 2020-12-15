package exception.game;

public class NotYourTurn extends Exception {
    public NotYourTurn() {
        super("Not your turn!");
    }
}
