package exception;

public class FriendNotFound extends Exception {
    public FriendNotFound() {
        super("You don't have friend with this username!");
    }
}
