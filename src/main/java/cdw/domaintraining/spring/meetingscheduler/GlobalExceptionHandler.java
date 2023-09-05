package cdw.domaintraining.spring.meetingscheduler;

import cdw.domaintraining.spring.meetingscheduler.exceptions.*;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CapacityMismatchException.class)
    public ResponseEntity<ErrorResponse> handleCapacityException(CapacityMismatchException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There is a capacity mismatch");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeEngagedException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeEngagedException(EmployeeEngagedException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Some employees have another method scheduled");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoMeetingScheduledException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMeetingException(NoMeetingScheduledException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No such meeting was scheduled");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchRoomFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRoomException(NoSuchRoomFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No such room exists");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchTimeSlotException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTimeSlotException(NoSuchTimeSlotException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No such Time slot exists");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SlotOccupiedException.class)
    public ResponseEntity<ErrorResponse> handleOccupiedSlotxception(SlotOccupiedException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "The slot is already occupied");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MeetingFinishedException.class)
    public ResponseEntity<ErrorResponse> handleMeetingFinishedException(MeetingFinishedException e){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "The meeting already got over");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}

