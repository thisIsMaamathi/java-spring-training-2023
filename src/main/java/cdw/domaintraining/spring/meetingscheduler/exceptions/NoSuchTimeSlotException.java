package cdw.domaintraining.spring.meetingscheduler.exceptions;

public class NoSuchTimeSlotException extends Exception {
    public NoSuchTimeSlotException(String msg) {
        super(msg);
    }

}
