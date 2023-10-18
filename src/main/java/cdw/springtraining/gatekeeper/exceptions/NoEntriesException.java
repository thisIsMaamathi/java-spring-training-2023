package cdw.springtraining.gatekeeper.exceptions;
/**
 * This custom exception class represents a situation where no entries are found.
 */
public class NoEntriesException extends RuntimeException {
    private String msg;
    public NoEntriesException(String message) {
        super(message);
        this.msg = msg;
    }
}
