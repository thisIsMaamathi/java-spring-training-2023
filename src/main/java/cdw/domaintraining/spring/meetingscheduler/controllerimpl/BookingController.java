package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.BookingControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookColabMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.BookTeamMeetingRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.BookingServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class BookingController implements BookingControllerInterface {
    BookingServices bookingServices;
    @Autowired
   public BookingController(BookingServices bookingServices){
       this.bookingServices=bookingServices;

   }

   @PostMapping("/team")
    public ResponseEntity<Object> bookMeetingForTeam(@RequestBody BookTeamMeetingRequest request)  {
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        Object object=bookingServices.bookForTeam(request.getEmployeeId(), request.getRoomId(),timeSlotRequest, request.getTeamId(), request.getDesc());
        return ResponseEntity.status(HttpStatus.OK).body(object);
   }

    @PostMapping("/colab")
    public ResponseEntity<Object> bookMeetingForCollab(@RequestBody BookColabMeetingRequest request){
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        Object object=bookingServices.bookOutsideTeam(request.getEmployeeId(), request.getRoomId(),timeSlotRequest,request.getCollaborators(), request.getDesc());
        return ResponseEntity.ok(object);
    }

    @DeleteMapping("/{timeslotId}")
    public ResponseEntity<Object> cancelmeet(@PathVariable int timeslotId){
        Object object=bookingServices.cancelMeeting(timeslotId);
        return ResponseEntity.ok(object);
    }





}
