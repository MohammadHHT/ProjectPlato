package exception;

public class UsernameIsAlreadyTaken extends Exception {
    public UsernameIsAlreadyTaken() {
        super("An account already exists with this username!");
    }
}
