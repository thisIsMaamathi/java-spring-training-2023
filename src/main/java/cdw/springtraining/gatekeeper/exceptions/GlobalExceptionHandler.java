package cdw.springtraining.gatekeeper.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlealreadyExsitsException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BlackListedUserException.class)
    public ResponseEntity<ErrorResponse> handleblacklistedException(BlackListedUserException ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlenotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("404", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserHasBeenRemovedException.class)
    public ResponseEntity<ErrorResponse> handleRemovedException(UserHasBeenRemovedException ex) {
        ErrorResponse errorResponse = new ErrorResponse("404", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoEntriesException.class)
    public ResponseEntity<ErrorResponse> handleNoEntriesException(NoEntriesException ex) {
        ErrorResponse errorResponse = new ErrorResponse("404", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleNoValidationException(ConstraintViolationException ex) {
        String message = String.valueOf(new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage());
        ErrorResponse errorResponse = new ErrorResponse("400", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
