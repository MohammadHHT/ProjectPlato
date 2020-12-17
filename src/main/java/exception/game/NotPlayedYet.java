package exception.game;

public class NotPlayedYet extends Exception {
    public NotPlayedYet() {
        super("You should play first!");
    }
}
