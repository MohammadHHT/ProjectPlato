package Exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException() {
        super("There is no game with this name!");
    }
}
