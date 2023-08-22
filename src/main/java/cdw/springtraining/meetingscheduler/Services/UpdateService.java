package cdw.springtraining.meetingscheduler.Services;

import cdw.springtraining.meetingscheduler.Entities.TimeSlot;
import cdw.springtraining.meetingscheduler.Entities.TimeSlotRequest;
import cdw.springtraining.meetingscheduler.Repository.EmployeeRepository;
import cdw.springtraining.meetingscheduler.Repository.RoomRepository;
import cdw.springtraining.meetingscheduler.Repository.TeamRepository;
import cdw.springtraining.meetingscheduler.Repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;
    private TeamRepository teamRepository;
    private TimeSlotRepository timeSlotRepository;
    private BookingService bookingService;

    @Autowired
    public UpdateService(EmployeeRepository employeeRepository, RoomRepository roomRepository, TeamRepository teamRepository, TimeSlotRepository timeSlotRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Autowired
    public void setBookingServices(BookingService bookingService){
        this.bookingService=bookingService;
    }

    public UpdateService(){

    }

    public String updateMeetingTime(int timeSlotid, TimeSlotRequest request){
        Optional<TimeSlot> timeslot=timeSlotRepository.findById(timeSlotid);
        List<TimeSlot> slots=timeSlotRepository.findAll();
        if(bookingService.sessionAvailable(slots,request.getStart_time(),request.getEnd_time(),request.getDate())){
            timeslot.get().setStartTime(request.getStart_time());
            timeslot.get().setEndTime(request.getEnd_time());
            timeslot.get().setDate(request.getDate());
        }

      return "Meeting Updated";
    }



}
