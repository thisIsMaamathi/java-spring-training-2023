package cdw.domaintraining.spring.meetingscheduler.serviceinterface;

import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;

import java.time.LocalDate;

public interface ViewServiceInterface {
    public ViewMeetingsResponse findTimeSlotId(TimeSlotRequest request, int roomId);
    public ViewMeetingsResponse findAllMeetingsByRoom (int roomId);
    public ViewMeetingsResponse findAllMeetingsByDate(LocalDate date);
    public ViewMeetingsResponse findAllMeetings();
}
