package cdw.springtraining.moviebooking.exception;

public class AccessDeniedException extends Exception{
    public AccessDeniedException(String msg){
        super(msg);
    }

}
