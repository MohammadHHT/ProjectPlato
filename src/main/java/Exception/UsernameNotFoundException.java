package Exception;

public class UsernameNotFoundException extends Exception{
    public UsernameNotFoundException() {
        super("Username not found!");
    }
}
