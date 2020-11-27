package exception;

public class UsernameIsAlreadyTakenException extends Exception {
    public UsernameIsAlreadyTakenException() {
        super("An account already exists with this username!");
    }
}
