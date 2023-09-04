package cdw.domaintraining.spring.meetingscheduler.serviceinterface;

import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;

public interface UpdatingServiceInterface {


    public Object changeMeetingTime(int timeslotId,TimeSlotRequest request);
    public Object changeMeetingRoom(int timeslotId,int roomId);

    public Object changeMeetingName(int timeSlotId,String name);


}
