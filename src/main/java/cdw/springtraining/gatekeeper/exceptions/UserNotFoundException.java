package cdw.springtraining.gatekeeper.exceptions;
/**
 * This custom exception class represents a situation where a user is not found.
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
