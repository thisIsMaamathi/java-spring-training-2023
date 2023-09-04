package cdw.domaintraining.spring.meetingscheduler.controllerimpl;

import cdw.domaintraining.spring.meetingscheduler.controllerinterface.UpdateControllerInterface;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.BookingServices;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.UpdatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{timeslotId}/{roomId}")
    public ResponseEntity<Object> changeRoom(@PathVariable int timeslotId, @PathVariable int roomId) {
        return ResponseEntity.ok(updatingService.changeMeetingRoom(timeslotId, roomId));
    }

    @PutMapping("/name/{timeslotId}")
    public ResponseEntity<Object> changeName(@PathVariable int timeslotId, @RequestBody String name) {
        return ResponseEntity.ok(updatingService.changeMeetingName(timeslotId, name));
    }

    @PutMapping("/timing/{timeslotId}")
    public ResponseEntity<Object> changeTime(@PathVariable int timeslotId, @RequestBody TimeSlotRequest request) {
        return ResponseEntity.ok(updatingService.changeMeetingTime(timeslotId, request));
    }


}
