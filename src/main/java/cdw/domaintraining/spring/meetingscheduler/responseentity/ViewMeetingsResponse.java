package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class ViewMeetingsResponse {

    List<TimeSlot> meetings=new ArrayList<>();

    public ViewMeetingsResponse(List<TimeSlot> meetings) {
        this.meetings = meetings;
    }
    public List<TimeSlot> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<TimeSlot> meetings) {
        this.meetings = meetings;
    }

}
