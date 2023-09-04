package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateControllerInterface {
    public ResponseEntity<Object> changeRoom(@PathVariable int  timeslotId, @PathVariable int roomId);
    public ResponseEntity<Object> changeName(@PathVariable int timeslotId,@RequestBody String name);

    public ResponseEntity<Object> changeTime(@PathVariable int timeslotId, @RequestBody TimeSlotRequest request);


    }
