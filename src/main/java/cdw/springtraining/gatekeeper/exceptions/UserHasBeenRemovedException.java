package cdw.springtraining.gatekeeper.exceptions;
/**
 * This custom exception class represents a situation where a user has been removed.
 */
public class UserHasBeenRemovedException extends RuntimeException{
    public UserHasBeenRemovedException(String message) {
        super(message);
    }
}
