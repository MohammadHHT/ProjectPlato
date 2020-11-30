package exception;

public class ThisUserIsNotYourFriendException extends Exception{
    public ThisUserIsNotYourFriendException() {
        super("This user isn't your friend.");
    }
}
