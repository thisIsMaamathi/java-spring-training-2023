package cdw.domaintraining.spring.meetingscheduler.serviceinterface;

import cdw.domaintraining.spring.meetingscheduler.entities.Employee;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.exceptions.*;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BookingServicesInterface {
    public Object bookForTeam(int employeeId,int roomID, TimeSlotRequest request,int teamId,String desc) throws JsonProcessingException, EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException;
    public Object bookOutsideTeam(int employeeId, int roomId, TimeSlotRequest request, List<Integer> employeeList,String desc) throws EmployeeEngagedException, SlotOccupiedException, CapacityMismatchException;
    public Object cancelMeeting(int timeSlotId) throws MeetingFinishedException,NoMeetingScheduledException;

}
