package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.ViewControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.requestentity.FindTimeSlotIdRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.ViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/view")
public class ViewController implements ViewControllerInterface {

    ViewServices viewservices;

    @Autowired
    public ViewController(ViewServices viewServices){
        this.viewservices=viewServices;
    }

    @Override
    @GetMapping("/timeslotId")
    public ResponseEntity<ViewMeetingsResponse> findingTimeSlotId(@RequestBody FindTimeSlotIdRequest request) {
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        return ResponseEntity.ok(viewservices.findTimeSlotId(timeSlotRequest,request.getRoomId())) ;
    }



    @Override
    @GetMapping("/{roomId}")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(viewservices.findAllMeetingsByRoom(roomId));
    }

    @Override
    @GetMapping("/date")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok(viewservices.findAllMeetingsByDate(date));
    }

    @Override
    @GetMapping("/allmeetings")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetings() {
        return ResponseEntity.ok(viewservices.findAllMeetings());
    }
}
