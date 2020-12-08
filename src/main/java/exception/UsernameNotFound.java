package exception;

public class UsernameNotFound extends Exception {
    public UsernameNotFound() {
        super("Username not found!");
    }
}
