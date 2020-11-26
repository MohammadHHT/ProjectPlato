package Exception;

public class EventIDNotFoundException extends Exception{
    public EventIDNotFoundException() {
        super("There is no event with this ID!");
    }
}
