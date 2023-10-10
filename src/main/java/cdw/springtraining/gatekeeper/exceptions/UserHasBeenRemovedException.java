package cdw.springtraining.gatekeeper.exceptions;

public class UserHasBeenRemovedException extends RuntimeException{
    public UserHasBeenRemovedException(String message) {
        super(message);
    }
}
