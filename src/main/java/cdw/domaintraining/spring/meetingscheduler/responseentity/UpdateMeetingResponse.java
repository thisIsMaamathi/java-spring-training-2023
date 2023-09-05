package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.UpdatingService;

public class UpdateMeetingResponse {
    TimeSlot timeSlot;
    String message;

    public UpdateMeetingResponse(TimeSlot timeSlot, String message) {
        this.timeSlot = timeSlot;
        this.message = message;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
