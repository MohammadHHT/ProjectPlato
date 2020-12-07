package exception;

public class AlreadyYourFriend extends Exception {
    public AlreadyYourFriend() {
        super("This user is already your friend!");
    }
}
