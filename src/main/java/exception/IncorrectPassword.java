package exception;

public class IncorrectPassword extends Exception {
    public IncorrectPassword() {
        super("Password is incorrect!");
    }
}
