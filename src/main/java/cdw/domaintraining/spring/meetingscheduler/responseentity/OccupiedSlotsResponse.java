package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class OccupiedSlotsResponse {
    List<TimeSlot> occupiedSlots=new ArrayList<>();

    public OccupiedSlotsResponse(List<TimeSlot> occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public List<TimeSlot> getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(List<TimeSlot> occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }
}
