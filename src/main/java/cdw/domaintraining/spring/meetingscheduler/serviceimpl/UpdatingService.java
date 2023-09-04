package cdw.domaintraining.spring.meetingscheduler.serviceimpl;

import cdw.domaintraining.spring.meetingscheduler.MeetingschedulerApplication;
import cdw.domaintraining.spring.meetingscheduler.entities.Room;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.repositories.EmployeeRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.RoomRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TeamRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TimeSlotRepository;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.UpdateMeetingResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceinterface.UpdatingServiceInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdatingService implements UpdatingServiceInterface {
    private static final Logger logger = LogManager.getLogger(MeetingschedulerApplication.class);
    TimeSlotRepository timeSlotRepository;
    TeamRepository teamRepository;
    EmployeeRepository employeeRepository;
    RoomRepository roomRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BookingServices bookingServices;

    @Autowired
    public UpdatingService(TeamRepository teamRepository, EmployeeRepository employeeRepository, TimeSlotRepository timeSlotRepository, RoomRepository roomRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.roomRepository = roomRepository;
    }

    UpdatingService() {
    }


    /**
     * Method to change the timing of the meeting
     * @param timeslotId
     * @param request
     * @return changed timeslot with updated timings
     */

    @Override
    public Object changeMeetingTime(int timeslotId, TimeSlotRequest request) {
        Optional<TimeSlot> timeSlot=timeSlotRepository.findById(timeslotId);
        if(timeSlot.isPresent()){
          if(bookingServices.isSlotAvailable(request,timeSlot.get().getRoomId())){
           timeSlot.get().setStart_time(request.getStart_time());
           timeSlot.get().setEnd_time(request.getEnd_time());
           timeSlotRepository.save(timeSlot.get());
           logger.info("new timeslot saved ");
           return new UpdateMeetingResponse(timeSlot.get());
          }
          else{
              logger.info("The slot you ask for is occupied");
              return new UpdateMeetingResponse("The slot you ask for is occupied");
          }
        }
        else{
            logger.info("Timeslot not present");
            return new UpdateMeetingResponse("Timeslot not present");
        }
    }

    /**
     * Method to change the meeting room
     * @param timeslotId
     * @param roomId
     * @return timeslot with changed room
     */

    @Override
    public Object changeMeetingRoom(int timeslotId, int roomId) {
        Optional<TimeSlot> timeSlot=timeSlotRepository.findById(timeslotId);
        if(timeSlot.isPresent()){
            TimeSlotRequest request =new TimeSlotRequest(timeSlot.get().getDate(),timeSlot.get().getStart_time(),timeSlot.get().getEnd_time());
            if (bookingServices.isSlotAvailable(request,roomId)){
                timeSlot.get().setRoomId(roomId);
                timeSlotRepository.save(timeSlot.get());
                logger.info("Changed meeting to room "+ timeSlot.get().getRoomId());
                return new UpdateMeetingResponse(timeSlot.get());
            }
            else{
                logger.info("the requested room is occupied,try another");
                return new UpdateMeetingResponse( "The requested room is occupied,try another");
            }

        }
        else {
            logger.info("TimeSlot not present");
            return new UpdateMeetingResponse("TimeSlot not present");
        }

    }

    /**
     * Method to change meeting name
     * @param timeSlotId
     * @param name
     * @return timeslot with changed name
     */

    @Override
    public Object changeMeetingName(int timeSlotId, String name) {
        Optional<TimeSlot> timeSlot=timeSlotRepository.findById(timeSlotId);
        if(timeSlot.isPresent()){
            timeSlot.get().setDesc(name);
            timeSlotRepository.save(timeSlot.get());
            logger.info("Changed name to "+timeSlot.get().getDesc());
            return new UpdateMeetingResponse( timeSlot.get());
        }
        else{
            logger.info("No time slot is present");
            return new UpdateMeetingResponse("No time slot is present");
        }

    }

}
