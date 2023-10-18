package cdw.springtraining.gatekeeper.exceptions;
/**
 * This custom exception class represents a situation where a user already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
