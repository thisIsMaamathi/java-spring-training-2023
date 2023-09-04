package cdw.domaintraining.spring.meetingscheduler.serviceimpl;

import cdw.domaintraining.spring.meetingscheduler.MeetingschedulerApplication;
import cdw.domaintraining.spring.meetingscheduler.entities.Room;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.repositories.EmployeeRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.RoomRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TeamRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TimeSlotRepository;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceinterface.ViewServiceInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ViewServices implements ViewServiceInterface {
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
    public ViewServices(TeamRepository teamRepository, EmployeeRepository employeeRepository, TimeSlotRepository timeSlotRepository, RoomRepository roomRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.roomRepository = roomRepository;
    }

    public ViewServices() {
    }

    /**
     * Method to retrieve timeslot id from timeslotRequest given
     * @param request
     * @param roomId
     * @return returns timeslot id for the given request
     */

    @Override
    public ViewMeetingsResponse findTimeSlotId(TimeSlotRequest request, int roomId) {
        Optional<Room> room = roomRepository.findById(roomId);


        if (room.isPresent()) {
            List<TimeSlot> meetingsOnDate = timeSlotRepository.findAllByRoomIdAndDate(roomId, request.getDate());

            Optional<TimeSlot> matchingSlot = meetingsOnDate.stream().filter(
                    timeSlot -> (timeSlot.getStart_time().equals(request.getStart_time())) && (timeSlot.getEnd_time().equals(request.getEnd_time()))).findFirst();

            if (matchingSlot.isPresent()) {
                logger.info("the meeting id is" + matchingSlot.get().getTimeSlotId());
                return new ViewMeetingsResponse(matchingSlot.get());

            } else {
                logger.info("No timeslot exists");
                return new ViewMeetingsResponse("No such timeslot exists");
            }

        } else {
            logger.info("Room not found");
            return new ViewMeetingsResponse("Room not found");
        }
    }

    /**
     * Method to find all meetings
     * @return a list of meetings
     */
    @Override
    public ViewMeetingsResponse findAllMeetings() {

        List<TimeSlot> meetings = timeSlotRepository.findAll();

        if (!meetings.isEmpty()) {
            logger.info("Returning all meetings");
            return new ViewMeetingsResponse(meetings);
        } else {
            logger.info("No meetings scheduled ");
            return new ViewMeetingsResponse("There are no meetings scheduled");

        }
    }

    /**
     * Method to find a list of meetings by date
     * @param date
     * @return list of meetings scheduled on that date
     */
    @Override
    public ViewMeetingsResponse findAllMeetingsByDate(LocalDate date) {

        List<TimeSlot> meetings = timeSlotRepository.findAllByDate(date);

        if (!meetings.isEmpty()) {
            logger.info("Returning all meetings");
            return new ViewMeetingsResponse(meetings);
        } else {
            logger.info("No meetings scheduled ");
            return new ViewMeetingsResponse("There are no meetings scheduled");

        }
    }

    /**
     * Method to find a list of meetings booked in a particular room
     * @param roomId
     * @return a  list of rooms
     */
    @Override
    public ViewMeetingsResponse findAllMeetingsByRoom (int roomId){
        List<TimeSlot> meetings=timeSlotRepository.findAllByRoomId(roomId);

        if (!meetings.isEmpty()) {
            logger.info("Returning all meetings");
            return new ViewMeetingsResponse(meetings);
        } else {
            logger.info("No meetings scheduled ");
            return new ViewMeetingsResponse("There are no meetings scheduled");
        }
    }


}










