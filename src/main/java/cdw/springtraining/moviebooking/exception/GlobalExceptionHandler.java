package cdw.springtraining.moviebooking.exception;

import cdw.springtraining.moviebooking.responseobjects.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
//        ErrorResponse errorResponse=new ErrorResponse("500","Internal Server Error");
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException(InvalidInputException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleDeniedException(AccessDeniedException ex) {
        ErrorResponse errorResponse=new ErrorResponse("401", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCredException(UsernameNotFoundException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShowAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleShowExistsException(ShowAlreadyExistsException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ElementNotFoundException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CapacityFullException.class)
    public ResponseEntity<ErrorResponse> handleCapacityFullException(CapacityFullException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HasNotBookedException.class)
    public ResponseEntity<ErrorResponse> handlehasNotBookedException(HasNotBookedException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CannotBookPrimeLocationException.class)
    public ResponseEntity<ErrorResponse> handleCapacityFullException(CannotBookPrimeLocationException ex) {
        ErrorResponse errorResponse=new ErrorResponse("500", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
