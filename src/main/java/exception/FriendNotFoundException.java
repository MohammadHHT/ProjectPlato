package exception;

public class FriendNotFoundException extends Exception {
    public FriendNotFoundException() {
        super("There is no one with this info to request!");
    }
}
