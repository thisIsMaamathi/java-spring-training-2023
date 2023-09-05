package cdw.domaintraining.spring.meetingscheduler.serviceinterface;

import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.SlotOccupiedException;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;

public interface UpdatingServiceInterface {


    public Object changeMeetingTime(int timeslotId,TimeSlotRequest request) throws SlotOccupiedException, NoSuchTimeSlotException;
    public Object changeMeetingRoom(int timeslotId,int roomId) throws SlotOccupiedException, NoSuchTimeSlotException;

    public Object changeMeetingName(int timeSlotId,String name) throws NoSuchTimeSlotException;


}
