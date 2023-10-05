package cdw.springtraining.gatekeeper.exceptions;

public class BlackListedUserException extends Exception{
    public BlackListedUserException(String message) {
        super(message);
    }
}
