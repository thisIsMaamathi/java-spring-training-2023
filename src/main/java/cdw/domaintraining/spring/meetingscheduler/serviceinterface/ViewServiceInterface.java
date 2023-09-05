package cdw.domaintraining.spring.meetingscheduler.serviceinterface;

import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchRoomFoundException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoMeetingScheduledException;
import cdw.domaintraining.spring.meetingscheduler.responseentity.TimeSlotResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;

import java.time.LocalDate;

public interface ViewServiceInterface {
    public TimeSlotResponse findTimeSlotId(TimeSlotRequest request, int roomId) throws NoSuchTimeSlotException, NoSuchRoomFoundException;
    public ViewMeetingsResponse findAllMeetingsByRoom (int roomId) throws NoMeetingScheduledException;
    public ViewMeetingsResponse findAllMeetingsByDate(LocalDate date) throws NoMeetingScheduledException;
    public ViewMeetingsResponse findAllMeetings() throws Exception;
}
