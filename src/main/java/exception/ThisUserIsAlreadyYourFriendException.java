package exception;

public class ThisUserIsAlreadyYourFriendException extends Exception {
    public ThisUserIsAlreadyYourFriendException() {
        super("This user is already your friend!");
    }
}
