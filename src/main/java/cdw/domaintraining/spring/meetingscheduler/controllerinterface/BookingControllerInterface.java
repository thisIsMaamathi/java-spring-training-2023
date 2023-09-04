package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.requestentity.BookColabMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookTeamMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookingControllerInterface {
    public ResponseEntity<Object> bookMeetingForTeam(@RequestBody BookTeamMeetingRequest bookTeamMeetingRequest);
    public ResponseEntity<Object> bookMeetingForCollab(@RequestBody BookColabMeetingRequest bookColabMeetingRequest);

    public ResponseEntity<Object> cancelmeet(@PathVariable int timeslotId);

}
