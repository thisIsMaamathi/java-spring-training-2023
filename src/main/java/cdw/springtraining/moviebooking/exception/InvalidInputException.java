package cdw.springtraining.moviebooking.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String msg){
        super(msg);
    }
}
