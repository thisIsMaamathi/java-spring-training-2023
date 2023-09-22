package cdw.springtraining.moviebooking.exception;

public class CapacityFullException extends Exception{
    public CapacityFullException(String msg){
        super(msg);
    }
}
