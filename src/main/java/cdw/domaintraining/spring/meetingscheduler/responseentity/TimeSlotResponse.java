package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;

public class TimeSlotResponse {
    TimeSlot timeSlot;

    public TimeSlotResponse(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
