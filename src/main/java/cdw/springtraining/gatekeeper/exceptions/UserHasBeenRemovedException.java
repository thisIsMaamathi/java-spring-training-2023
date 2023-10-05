package cdw.springtraining.gatekeeper.exceptions;

public class UserHasBeenRemovedException extends Exception{
    public UserHasBeenRemovedException(String message) {
        super(message);
    }
}
