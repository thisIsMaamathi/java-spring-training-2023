package cdw.springtraining.moviebooking.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String msg){
        super(msg);
    }
}
