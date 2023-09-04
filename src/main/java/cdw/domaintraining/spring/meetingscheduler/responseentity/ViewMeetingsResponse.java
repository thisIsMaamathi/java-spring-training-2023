package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class ViewMeetingsResponse {

    List<TimeSlot> meetings=new ArrayList<>();
    String message;

    TimeSlot meeting;

    public ViewMeetingsResponse(TimeSlot meeting) {
        this.meeting = meeting;
    }

    public ViewMeetingsResponse(String message) {
        this.message = message;
    }

    public ViewMeetingsResponse(List<TimeSlot> meetings) {
        this.meetings = meetings;
    }

    public TimeSlot getMeeting() {
        return meeting;
    }

    public void setMeeting(TimeSlot meeting) {
        this.meeting = meeting;
    }

    public List<TimeSlot> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<TimeSlot> meetings) {
        this.meetings = meetings;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
