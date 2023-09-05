package cdw.domaintraining.spring.meetingscheduler.exceptions;

import cdw.domaintraining.spring.meetingscheduler.responseentity.OccupiedSlotsResponse;

public class SlotOccupiedException extends Exception{
    public SlotOccupiedException(String msg){
        super(msg);
    }
}
