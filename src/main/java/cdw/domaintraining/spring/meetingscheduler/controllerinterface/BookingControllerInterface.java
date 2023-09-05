package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.exceptions.*;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookColabMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookTeamMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.BookForColabMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.BookForTeamResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.CancelMeetingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingControllerInterface {
    public ResponseEntity<BookForTeamResponse> bookMeetingForTeam(@RequestBody BookTeamMeetingRequest bookTeamMeetingRequest) throws EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException;
    public ResponseEntity<BookForColabMeetingResponse> bookMeetingForCollab(@RequestBody BookColabMeetingRequest bookColabMeetingRequest) throws EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException;

    public ResponseEntity<CancelMeetingResponse> cancelmeet(@PathVariable int timeslotId) throws NoMeetingScheduledException, MeetingFinishedException;

}
