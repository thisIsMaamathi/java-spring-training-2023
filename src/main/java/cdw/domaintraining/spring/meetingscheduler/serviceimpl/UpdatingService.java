package cdw.domaintraining.spring.meetingscheduler.serviceimpl;

import cdw.domaintraining.spring.meetingscheduler.MeetingschedulerApplication;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.exceptions.NoSuchTimeSlotException;
import cdw.domaintraining.spring.meetingscheduler.exceptions.SlotOccupiedException;
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

import java.util.Optional;

/**
 * This service class provides methods to edit the details of an existing meeting
 */
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
/**
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
     *
     * @param timeslotId
     * @param request
     * @return changed timeslot with updated timings
     */

    @Override
    public UpdateMeetingResponse changeMeetingTime(int timeslotId, TimeSlotRequest request) throws SlotOccupiedException, NoSuchTimeSlotException {
        Optional<TimeSlot> timeSlot = Optional.of(timeSlotRepository.findById(timeslotId)).orElseThrow();
        if (timeSlot.isPresent()) {
            if (bookingServices.isSlotAvailable(request, timeSlot.get().getRoomId())) {
                timeSlot.get().setStart_time(request.getStart_time());
                timeSlot.get().setEnd_time(request.getEnd_time());
                timeSlotRepository.save(timeSlot.get());
                logger.info("new timeslot saved ");
                return new UpdateMeetingResponse(timeSlot.get(), "Changed slot time");
            } else {
                logger.info("The slot you ask for is occupied");
                throw new SlotOccupiedException("The slot you ask for is occupied");
            }
        } else {
            logger.info("Timeslot not present");
            throw new NoSuchTimeSlotException ("Timeslot not present");
        }
    }

    /**
     * Method to change the meeting room
     *
     * @param timeslotId
     * @param roomId
     * @return timeslot with changed room
     */

    @Override
    public UpdateMeetingResponse changeMeetingRoom(int timeslotId, int roomId) throws SlotOccupiedException, NoSuchTimeSlotException {
        Optional<TimeSlot> timeSlot = Optional.of(timeSlotRepository.findById(timeslotId)).orElseThrow();
        if (timeSlot.isPresent()) {
            TimeSlotRequest request = new TimeSlotRequest(timeSlot.get().getDate(), timeSlot.get().getStart_time(), timeSlot.get().getEnd_time());
            if (bookingServices.isSlotAvailable(request, roomId)) {
                timeSlot.get().setRoomId(roomId);
                timeSlotRepository.save(timeSlot.get());
                logger.info("Changed meeting to room " + timeSlot.get().getRoomId());
                return new UpdateMeetingResponse(timeSlot.get(),"Changed Meeting to requested room");
            } else {
                logger.info("the requested room is occupied,try another");
                throw new SlotOccupiedException("the requested room is occupied,try another");
            }

        } else {
            logger.info("TimeSlot not present");
            throw new NoSuchTimeSlotException("TimeSlot not present");
        }

    }

    /**
     * Method to change meeting name
     *
     * @param timeSlotId
     * @param name
     * @return timeslot with changed name
     */

    @Override
    public UpdateMeetingResponse changeMeetingName(int timeSlotId, String name) throws NoSuchTimeSlotException {
        Optional<TimeSlot> timeSlot = Optional.of(timeSlotRepository.findById(timeSlotId)).orElseThrow();
        if (timeSlot.isPresent()) {
            timeSlot.get().setDesc(name);
            timeSlotRepository.save(timeSlot.get());
            logger.info("Changed name to " + timeSlot.get().getDesc());
            return new UpdateMeetingResponse(timeSlot.get(),"Meeting name changed");
        } else {
            logger.info("No time slot is present");
          throw new NoSuchTimeSlotException("No time slot is present");
        }

    }

}
