package exception.game;

public class NotYourTurn extends Exception {
    NotYourTurn() {
        super("Not your turn!");
    }
}
