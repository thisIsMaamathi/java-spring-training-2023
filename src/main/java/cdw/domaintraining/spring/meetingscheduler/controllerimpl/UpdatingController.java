package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.UpdateControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.SlotOccupiedException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.BookingServices;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.UpdatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that allows the user to update details of a booked meeting
 */
@RestController
@RequestMapping("/update")
public class UpdatingController implements UpdateControllerInterface {
    BookingServices bookingServices;
    UpdatingService updatingService;

    @Autowired
    public UpdatingController(BookingServices bookingServices, UpdatingService updatingService) {
        this.bookingServices = bookingServices;
        this.updatingService = updatingService;

    }

    public UpdatingController() {
    }

    /**
     * Method to change the room of an existing meeting
     * @param timeslotId
     * @param roomId
     * @return New timslot with pdated room details in it.
     * @throws NoSuchTimeSlotException
     * @throws SlotOccupiedException
     */
    @PutMapping("/{timeslotId}/{roomId}")
    public ResponseEntity<UpdateMeetingResponse> changeRoom(@PathVariable int timeslotId, @PathVariable int roomId) throws NoSuchTimeSlotException, SlotOccupiedException {
        return ResponseEntity.ok(updatingService.changeMeetingRoom(timeslotId, roomId));
    }

    /**
     * Method that allows user to change the name of an existing meeting
     * @param timeslotId
     * @param name
     * @return Timeslot with updated name
     * @throws NoSuchTimeSlotException
     */
    @PutMapping("/name/{timeslotId}")
    public ResponseEntity<UpdateMeetingResponse> changeName(@PathVariable int timeslotId, @RequestBody String name) throws NoSuchTimeSlotException {
        return ResponseEntity.ok(updatingService.changeMeetingName(timeslotId, name));
    }

    /**
     * Method that allows the user to change the timings of the meeting wiothin the same room
     * @param timeslotId
     * @param request
     * @return Timeslot with udapte timimng details
     * @throws NoSuchTimeSlotException
     * @throws SlotOccupiedException
     */
    @PutMapping("/timing/{timeslotId}")
    public ResponseEntity<UpdateMeetingResponse> changeTime(@PathVariable int timeslotId, @RequestBody TimeSlotRequest request) throws NoSuchTimeSlotException, SlotOccupiedException {
        return ResponseEntity.ok(updatingService.changeMeetingTime(timeslotId, request));
    }


}
