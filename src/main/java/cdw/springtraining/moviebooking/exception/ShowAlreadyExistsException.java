package cdw.springtraining.moviebooking.exception;

public class ShowAlreadyExistsException extends Exception{
    public ShowAlreadyExistsException(String msg){
        super(msg);
    }
}
