package cdw.domaintraining.spring.meetingscheduler.controllerinterface;

import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.SlotOccupiedException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdateControllerInterface {
    public ResponseEntity<UpdateMeetingResponse> changeRoom(@PathVariable int  timeslotId, @PathVariable int roomId) throws NoSuchTimeSlotException, SlotOccupiedException;
    public ResponseEntity<UpdateMeetingResponse> changeName(@PathVariable int timeslotId, @RequestBody String name) throws NoSuchTimeSlotException;

    public ResponseEntity<UpdateMeetingResponse> changeTime(@PathVariable int timeslotId, @RequestBody TimeSlotRequest request) throws NoSuchTimeSlotException, SlotOccupiedException;


    }
