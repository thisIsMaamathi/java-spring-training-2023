package cdw.springtraining.meetingscheduler.Services;


import cdw.springtraining.meetingscheduler.Entities.*;
import cdw.springtraining.meetingscheduler.Repository.EmployeeRepository;
import cdw.springtraining.meetingscheduler.Repository.RoomRepository;
import cdw.springtraining.meetingscheduler.Repository.TeamRepository;
import cdw.springtraining.meetingscheduler.Repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    //conditions
    //count of members or team size-for room size
    //time slot check
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;
    private TeamRepository teamRepository;
    private TimeSlotRepository timeSlotRepository;
    public static int generator=9000;
@Autowired
    public BookingService(EmployeeRepository employeeRepository, RoomRepository roomRepository, TeamRepository teamRepository, TimeSlotRepository timeSlotRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.teamRepository = teamRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public BookingService(){}


    public String bookForTeam(int employeeId, int teamId, TimeSlotRequest timeSlotRequest, int roomId) {
      Optional<Employee> employee = employeeRepository.findById(employeeId);
      Optional<Teams> team = teamRepository.findById(teamId);

      LocalDate date=timeSlotRequest.getDate();
      LocalTime start_time=timeSlotRequest.getStart_time();
      LocalTime end_time=timeSlotRequest.getEnd_time();

      TimeSlot meetingSlot = new TimeSlot(employeeId, teamId, roomId, date, start_time, end_time, 1, "desc");
      boolean sessionAvailable = false;

      if (roomRepository.existsById(roomId)) {
            //condition-1: check for capacity
          Optional<Room> room=roomRepository.findById(roomId);
            //room has to be occpied atlest 75%
            double minimumRoomOccupancy=room.get().getRoomCapacity()*0.75;
            if(team.get().getTeamCount()>=minimumRoomOccupancy) {
                //condition-2: checking for any existing meeting
                List<TimeSlot> slots = timeSlotRepository.findAllByRoomId(roomId);
                if (slots.isEmpty()) {
                    blockMeeting(meetingSlot,employee.get(),team.get(),room.get());
                    return "Booking Meeting...no meeting has been scheduled in this room";
                } else {
                   boolean flag=sessionAvailable(slots,start_time,end_time,date);
                    //booking slots
                    if (flag) {
                        blockMeeting(meetingSlot,employee.get(),team.get(),room.get());
                        return "Booking Meeting";
                    } else return "Already booked this slot";
                }
            }
            else return "Check for the room capacity";

        }else return "Room doesnot exist";
    }


    public boolean sessionAvailable(List<TimeSlot> slots, LocalTime start_time, LocalTime end_time, LocalDate date){
    boolean sessionAvailable=false;
        for (TimeSlot timeSlot : slots) {
            //if date matches ...check time....else,if date doesnot match,return can book
            if (timeSlot.getDate().equals(date)) {
                //check for bookable slots
                //if meeting starts and ends before the slot,session available
                if ((start_time.isBefore(timeSlot.getStartTime())) && (end_time.isBefore(timeSlot.getStartTime()))) {
                    sessionAvailable = true;
                    //if meeting after the slot,session available
                } else if (start_time.isAfter(timeSlot.getEndTime())) {
                    sessionAvailable = true;
                } else {
                    sessionAvailable = false;
                }
            }
            else {
                sessionAvailable = true;
            }
        }
        return sessionAvailable;
    }



    public String bookOutsideTeam(int employeeId, ArrayList<Integer> meetingInvitees, TimeSlotRequest timeSlotRequest, int roomId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        LocalDate date = timeSlotRequest.getDate();
        LocalTime start_time = timeSlotRequest.getStart_time();
        LocalTime end_time = timeSlotRequest.getEnd_time();
        List<Employee> unavailableEmployees=new ArrayList<>();
        //generate a colab team
        unavailableEmployees=checkEmployeeAvailability(meetingInvitees,timeSlotRequest);

        if(unavailableEmployees.isEmpty()){
            Teams colabTeam=new Teams(generator," colabTeam"+generator, meetingInvitees.size());
            generator++;
            teamRepository.save(colabTeam);
            int teamId=colabTeam.getTeamId();
            bookForTeam(employeeId,teamId,timeSlotRequest,roomId);
        }
        else{
            System.out.println("Meeting cannot be scheduled as certain employees are busy");
        }
        return null;
    }





     public ArrayList<Employee> checkEmployeeAvailability(ArrayList<Integer> employeeArrayList,TimeSlotRequest timeslotRequest){
         ArrayList<Employee> unavailableEmployees = new ArrayList<>();
         List<TimeSlot> matchingTimeSlots = timeSlotRepository.findAllByDate(timeslotRequest.getDate());
         List<Employee> listEmployees=new ArrayList<>();
         for (Integer id:employeeArrayList) {
             Optional<Employee> employee = employeeRepository.findById(id);
             listEmployees.add(employee.get());

         }
         for (Employee employe : listEmployees) {
             for (TimeSlot timeSlot : matchingTimeSlots) {
                 if(!sessionAvailable(matchingTimeSlots,timeslotRequest.getStart_time(),timeslotRequest.getEnd_time(),timeslotRequest.getDate())) {
                     int teamID = timeSlot.getTeamId();
                     if (teamRepository.findEmployeeByTeamId(teamID)) {
                         unavailableEmployees.add(employe);
                     }
                 }

             }
         }
         return unavailableEmployees;
     }

    public void blockMeeting(TimeSlot timeSlot,Employee employee,Teams team,Room room){
         timeSlot.setEmployee(employee);
         timeSlot.addTeamtoTimeSlot(team);
         timeSlot.addRoomtoTimeSlot(room);
        timeSlotRepository.save(timeSlot);
        System.err.println("Saved ");
    }

    public String cancelMeeting(int timeslotId){
     if(timeSlotRepository.existsById(timeslotId)) {
         Optional<TimeSlot> timeSlot=timeSlotRepository.findById(timeslotId);
         if(LocalTime.now().minus(30, ChronoUnit.MINUTES).isBefore(timeSlot.get().getStartTime()))
         {timeSlotRepository.deleteById(timeslotId);
         System.out.println("Meeting cancelled");
         return "Success";}
         else return"Meeting too close";
     }
     else {
         System.out.println("Noo such meeting was scheduled");
     }
          return "Failed to delete";
    }

}
