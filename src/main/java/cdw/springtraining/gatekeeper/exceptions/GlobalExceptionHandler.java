package cdw.springtraining.gatekeeper.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("500", "Some problem occurred");
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
    public ResponseEntity<List<ValidationError>> handleValidationException(ConstraintViolationException ex) {
        List<ValidationError> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            ValidationError error = new ValidationError(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );
            errors.add(error);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
