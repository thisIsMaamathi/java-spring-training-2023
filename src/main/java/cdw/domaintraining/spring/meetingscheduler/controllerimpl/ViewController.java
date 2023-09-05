package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.ViewControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoMeetingScheduledException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchRoomFoundException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.FindTimeSlotIdRequest;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.TimeSlotResponse;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.ViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controller that shows list of meetings as per user's request
 */
@RestController
@RequestMapping("/view")
public class ViewController implements ViewControllerInterface {

    ViewServices viewservices;

    @Autowired
    public ViewController(ViewServices viewServices){
        this.viewservices=viewServices;
    }

    /**
     * Method returns the timeslotId for given timeslot details
     * @param request
     * @return timeslotId
     * @throws NoSuchTimeSlotException
     * @throws NoSuchRoomFoundException
     */
    @Override
    @GetMapping("/timeslotId")
    public ResponseEntity<TimeSlotResponse> findingTimeSlotId(@RequestBody FindTimeSlotIdRequest request) throws NoSuchTimeSlotException, NoSuchRoomFoundException {
        TimeSlotRequest timeSlotRequest=new TimeSlotRequest(request.getDate(),request.getStart_time(),request.getEnd_time());
        int roomId= request.getRoomId();
        return ResponseEntity.ok(viewservices.findTimeSlotId(timeSlotRequest,roomId)) ;
    }

    /**
     * Method that returns all booked meetings by room id
     * @param roomId
     * @return list of timeslots
     * @throws NoMeetingScheduledException
     */
    @Override
    @GetMapping("/{roomId}")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByRoom(@PathVariable int roomId) throws NoMeetingScheduledException {
        return ResponseEntity.ok(viewservices.findAllMeetingsByRoom(roomId));
    }

    /**
     * This method returns a list of  meeting for a given date
     * @param date
     * @return list of timeslots
     * @throws NoMeetingScheduledException
     */
    @Override
    @GetMapping("/date")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetingsByDate(@RequestBody LocalDate date) throws NoMeetingScheduledException {
        return ResponseEntity.ok(viewservices.findAllMeetingsByDate(date));
    }

    /**
     * Method returns a list of all meetings
     * @return list of timeslots
     * @throws Exception
     */
    @Override
    @GetMapping("/allmeetings")
    public ResponseEntity<ViewMeetingsResponse> findAllMeetings() throws Exception {
        return ResponseEntity.ok(viewservices.findAllMeetings());
    }
}
