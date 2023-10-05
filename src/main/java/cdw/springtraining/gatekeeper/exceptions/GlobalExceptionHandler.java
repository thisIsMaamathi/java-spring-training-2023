//package cdw.springtraining.gatekeeper.exceptions;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<ErrorResponse> handlealreadyExsitsException(UserAlreadyExistsException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(BlackListedUserException.class)
//    public ResponseEntity<ErrorResponse> handleblacklistedException(BlackListedUserException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handlenotFoundException(UserNotFoundException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UserHasBeenRemovedException.class)
//    public ResponseEntity<ErrorResponse> handleRemovedException(UserHasBeenRemovedException ex) {
//        ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
