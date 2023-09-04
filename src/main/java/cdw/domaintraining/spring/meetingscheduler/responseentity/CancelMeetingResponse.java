package cdw.domaintraining.spring.meetingscheduler.responseentity;

public class CancelMeetingResponse {
    String message;

    public CancelMeetingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
