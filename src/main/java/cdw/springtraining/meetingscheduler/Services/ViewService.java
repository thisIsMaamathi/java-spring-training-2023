package cdw.springtraining.meetingscheduler.Services;

import cdw.springtraining.meetingscheduler.Entities.TimeSlot;
import cdw.springtraining.meetingscheduler.Repository.EmployeeRepository;
import cdw.springtraining.meetingscheduler.Repository.RoomRepository;
import cdw.springtraining.meetingscheduler.Repository.TeamRepository;
import cdw.springtraining.meetingscheduler.Repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViewService {
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;
    private TeamRepository teamRepository;
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    public ViewService(EmployeeRepository employeeRepository, RoomRepository roomRepository, TeamRepository teamRepository, TimeSlotRepository timeSlotRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public ViewService(){}

    public List<TimeSlot> viewAllMeetings(){
        if(timeSlotRepository==null) System.out.println("No Meeting scheduled");
        else{
            return timeSlotRepository.findAll();
        }
        return null;
    }

    public List<TimeSlot> viewMeetingByDate(LocalDate date){
        List<TimeSlot> meetingSlots=new ArrayList<>();
        if(timeSlotRepository==null) System.out.println("No Meeting scheduled");
        else {

            meetingSlots=timeSlotRepository.findAllByDate(date);
            if(meetingSlots.isEmpty()) System.out.println("No meeting Scheduled on that date");

        }
        return meetingSlots;
    }



}
