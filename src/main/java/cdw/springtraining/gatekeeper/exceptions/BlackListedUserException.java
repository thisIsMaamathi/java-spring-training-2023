package cdw.springtraining.gatekeeper.exceptions;

public class BlackListedUserException extends RuntimeException{
    public BlackListedUserException(String message) {
        super(message);
    }
}
