package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.BookingControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.exceptions.*;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookColabMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookTeamMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.BookForColabMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.BookForTeamResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.CancelMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Controller that handles requests for booking and cancelling the meetings.
 */

@RestController
@RequestMapping("/meeting")
public class BookingController implements BookingControllerInterface {
    BookingServices bookingServices;
    @Autowired
    public BookingController(BookingServices bookingServices){
       this.bookingServices=bookingServices;

   }

    /**
     * Method that allows the user to book a meeting for an existing team
     * @param request
     * @return Response entity that contains details about booked slot
     * @throws EmployeeEngagedException
     * @throws SlotOccupiedException
     * @throws CapacityMismatchException
     */
   @PostMapping("/team")
    public ResponseEntity<BookForTeamResponse> bookMeetingForTeam(@RequestBody BookTeamMeetingRequest request) throws EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException {
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        return ResponseEntity.status(HttpStatus.OK).body(bookingServices.bookForTeam(request.getEmployeeId(), request.getRoomId(),timeSlotRequest, request.getTeamId(), request.getDesc()));

   }

    /**
     * Method that allows the user to book meeting for a colab team
     * @param request
     * @return response entity that contains details about slot
     * @throws EmployeeEngagedException
     * @throws SlotOccupiedException
     * @throws CapacityMismatchException
     */
    @PostMapping("/colab")
    public ResponseEntity<BookForColabMeetingResponse> bookMeetingForCollab(@RequestBody BookColabMeetingRequest request) throws EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException {
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        return ResponseEntity.ok(bookingServices.bookOutsideTeam(request.getEmployeeId(), request.getRoomId(),timeSlotRequest,request.getCollaborators(), request.getDesc()));
    }

    /**
     * Method that allow the user to cancel the meeting
     * @param timeslotId
     * @return Cancel Meeting response
     * @throws NoMeetingScheduledException
     * @throws MeetingFinishedException
     */

    @DeleteMapping("/{timeslotId}")
    public ResponseEntity<CancelMeetingResponse> cancelmeet(@PathVariable int timeslotId) throws NoMeetingScheduledException, MeetingFinishedException {
        return ResponseEntity.ok(bookingServices.cancelMeeting(timeslotId));
    }





}
