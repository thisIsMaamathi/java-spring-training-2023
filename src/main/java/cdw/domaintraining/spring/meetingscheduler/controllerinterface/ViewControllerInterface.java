package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.exceptions.NoMeetingScheduledException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchRoomFoundException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.FindTimeSlotIdRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.TimeSlotResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public interface ViewControllerInterface {



    ResponseEntity<TimeSlotResponse> findingTimeSlotId(@RequestBody FindTimeSlotIdRequest request) throws NoSuchTimeSlotException, NoSuchRoomFoundException;

    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByRoom (int roomId) throws NoMeetingScheduledException;
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByDate(LocalDate date) throws NoMeetingScheduledException;
    public ResponseEntity<ViewMeetingsResponse> findAllMeetings() throws Exception;

}
