package cdw.springtraining.gatekeeper.exceptions;

/**
 * This is a custom exception class representing a situation where a user is blacklisted.
 */
public class BlackListedUserException extends RuntimeException {
    public BlackListedUserException(String message) {
        super(message);
    }
}
