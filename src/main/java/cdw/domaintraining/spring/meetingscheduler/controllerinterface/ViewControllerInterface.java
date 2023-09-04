package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.requestentity.FindTimeSlotIdRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public interface ViewControllerInterface {



    ResponseEntity<ViewMeetingsResponse> findingTimeSlotId(@RequestBody FindTimeSlotIdRequest request);

    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByRoom (int roomId);
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByDate(LocalDate date);
    public ResponseEntity<ViewMeetingsResponse> findAllMeetings();

}
