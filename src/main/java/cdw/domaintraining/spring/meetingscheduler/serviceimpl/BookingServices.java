package cdw.domaintraining.spring.meetingscheduler.serviceimpl;

import cdw.domaintraining.spring.meetingscheduler.MeetingschedulerApplication;
import cdw.domaintraining.spring.meetingscheduler.entities.Employee;
import cdw.domaintraining.spring.meetingscheduler.entities.Room;
import cdw.domaintraining.spring.meetingscheduler.entities.Team;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.repositories.EmployeeRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.RoomRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TeamRepository;
import cdw.domaintraining.spring.meetingscheduler.repositories.TimeSlotRepository;
import cdw.domaintraining.spring.meetingscheduler.requestentity.TimeSlotRequest;
import cdw.domaintraining.spring.meetingscheduler.responseentity.*;
import cdw.domaintraining.spring.meetingscheduler.serviceinterface.BookingServicesInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServices implements BookingServicesInterface {
    private static final Logger logger = LogManager.getLogger(MeetingschedulerApplication.class);
    TimeSlotRepository timeSlotRepository;
    TeamRepository teamRepository;
    EmployeeRepository employeeRepository;
    RoomRepository roomRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public BookingServices(TeamRepository teamRepository, EmployeeRepository employeeRepository, TimeSlotRepository timeSlotRepository, RoomRepository roomRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.roomRepository = roomRepository;
    }

    public BookingServices() {
    }

    /**
     * Method that allows you to book meeting for a team
     *
     * @param employeeId
     * @param roomID
     * @param request
     * @param teamId
     * @param desc
     * @return timeslot if the meeting gets booked, Or,
     * List of unavailable employees if some member are engaged, or,
     * occupied slots of the room if the room is unavailable for that timeslot,Or,
     * next nearest room suiting the capacity of team members if the capacity mismatches
     */
    @Override
    @Transactional
    public Object bookForTeam(int employeeId, int roomID, TimeSlotRequest request, int teamId, String desc) {

        List<Employee> unavailableEmployee = new ArrayList<>();
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<Room> room = roomRepository.findById(roomID);
        Optional<Employee> employee1 = employeeRepository.findById(employeeId);
        int capacity = checkRoomCapacity(room.get(), team.get().getTeamCount());

        if (capacity == 1) {

            if (isSlotAvailable(request, roomID)) {

                unavailableEmployee = team.get().getTeamMembersList().stream()
                        .filter(employee -> isEmployeeUnAvailable(request, employee)).collect(Collectors.toList());

                if (unavailableEmployee.isEmpty()) {

                    TimeSlot timeSlot = new TimeSlot(teamId, roomID, request.getDate(), request.getStart_time(), request.getEnd_time(), desc, 1);
                    timeSlot.addRoomToTimeSlot(room.get());
                    timeSlot.addTeamToTimeSlot(team.get());
                    timeSlot.setEmployee(employee1.get());
                    entityManager.persist(timeSlot);
                    logger.info("booked slot");

                    return new BookForTeamResponse(timeSlot.getRoomId(), timeSlot.getTeamId(), timeSlot.getDate(), timeSlot.getStart_time(), timeSlot.getEnd_time(), timeSlot.getDesc());


                } else {
                    logger.info("Meeting cannot be booked,returning unavailable employees list");
                    return new UnavailableEmployeesResponse(unavailableEmployee);

                }


            } else {
                logger.info("Slot already occupied");
                List<TimeSlot> occupiedSlots = timeSlotRepository.findAllByRoomIdAndDate(roomID, request.getDate());
                return new OccupiedSlotsResponse(occupiedSlots); //occupied slots of the room
            }

        } else {
            logger.info("Capacity mismatch");
            Room nextNearestRoom = nextNearestRoom(team.get().getTeamCount(), room.get(), capacity);
            return new NextNearestRoomResponse(nextNearestRoom); //next nearest room for the team capacity
        }
    }

    /**
     * Method that allows you to book meeting involving members from different teams
     *
     * @param employeeId
     * @param roomId
     * @param request
     * @param employeeList
     * @param desc
     * @return timeslot if the meeting gets booked, Or,
     * List of unavailable employees if some member are engaged, or,
     * occupied slots of the room if the room is unavailable for that timeslot,Or,
     * next nearest room suiting the capacity of team members if the capacity mismatches
     */
    @Override
    @Transactional
    public Object bookOutsideTeam(int employeeId, int roomId, TimeSlotRequest request, List<Integer> employeeList, String desc) {

        Optional<Room> room = roomRepository.findById(roomId);
        List<Employee> unavailableEmployees = new ArrayList<>();
        Optional<Employee> employee1 = employeeRepository.findById(employeeId);

        List<Employee> collaborators = employeeList.stream().map(empId -> employeeRepository.findById(empId).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
        int capacity = checkRoomCapacity(room.get(), employeeList.size());
        if (capacity == 1) {
            if (isSlotAvailable(request, roomId)) {
                unavailableEmployees = collaborators.stream().filter(employee -> isEmployeeUnAvailable(request, employee)).collect(Collectors.toList());

                if (unavailableEmployees.isEmpty()) {
                    Team colabTeam = new Team(desc + "colabTeam", collaborators.size());
                    colabTeam.setTeamMembersList(collaborators);
                    teamRepository.save(colabTeam);
                    TimeSlot timeSlot = new TimeSlot(colabTeam.getTeamId(), roomId, request.getDate(), request.getStart_time(), request.getEnd_time(), desc, 1);
                    timeSlot.addRoomToTimeSlot(room.get());
                    timeSlot.addTeamToTimeSlot(colabTeam);
                    timeSlot.setEmployee(employee1.get());
                    entityManager.persist(timeSlot);
                    logger.info("booked slot");
                    return new BookForTeamResponse(timeSlot.getRoomId(), timeSlot.getTeamId(), timeSlot.getDate(), timeSlot.getStart_time(), timeSlot.getEnd_time(), timeSlot.getDesc());


                } else {
                    logger.info("Some employees are engaged");
                    return new UnavailableEmployeesResponse(unavailableEmployees);

                }

            } else {
                logger.info("Slot already occupied");
                List<TimeSlot> occupiedSlots = timeSlotRepository.findAllByRoomIdAndDate(roomId, request.getDate());
                return new OccupiedSlotsResponse(occupiedSlots);

            }

        } else {
            logger.info("Capacity mismatch");
            Room nextNearestRoom = nextNearestRoom(employeeList.size(), room.get(), capacity);
            return new NextNearestRoomResponse(nextNearestRoom);
        }
    }

    /**
     * Method that allows you to book the next nearest room in terms of capacity ,if the requested room is not able to accomodate the meeting attendees
     *
     * @param teamSize
     * @param room
     * @param capacity
     * @return the next nearest room
     */

    private Room nextNearestRoom(int teamSize, Room room, int capacity) {
        List<Room> rooms = roomRepository.findAll();
        Room nearestRoom;
        if (capacity == -1) {
            rooms = rooms.stream().filter(room1 -> room1.getRoomCapacity() > teamSize).collect(Collectors.toList());
            nearestRoom = rooms.get(0);

        } else {
            rooms = rooms.stream().filter(room1 -> (room.getRoomCapacity() > room1.getRoomCapacity()) && (room1.getRoomCapacity() > teamSize)).collect(Collectors.toList());
            nearestRoom = rooms.get(0);

        }
        return nearestRoom;

    }

    /**
     * Method that checks the room capacity,whether the given room will accommodate meeting attendees
     *
     * @param room
     * @param teamCount
     * @return 1 if the room can accomodate all attendees,
     * 0 if the room is too big wrt number of attendees
     * -1 if room is too small
     */

    private int checkRoomCapacity(Room room, int teamCount) {
        double threshold = 75;
        int roomSize = room.getRoomCapacity();
        if (teamCount <= roomSize) {

            double occupancy = ((double) teamCount / room.getRoomCapacity()) * 100;

            if (occupancy >= threshold) {
                //room can be booked
                return 1;
            }
            //room cannot be booked
            else return 0;

        }
        //room is too small to accomodate the team
        else return -1;

    }

    /**
     * Method allows you to check if a particular room is free within the timeframe of a timeslot
     *
     * @param request
     * @param roomId
     * @return true is the slot is avilable
     * false if not available
     */

    public boolean isSlotAvailable(TimeSlotRequest request, int roomId) {
        LocalDate date = request.getDate();
        LocalTime start_time = request.getStart_time();
        LocalTime end_time = request.getEnd_time();
        List<TimeSlot> OverLappingList = new ArrayList<>();

        List<TimeSlot> slots = timeSlotRepository.findAllByRoomIdAndDate(roomId, date);
        if (slots.isEmpty()) return true;
        else {
            //checks if the request slot can be available wrt to the timeslot from db,stores the boolean list of whether it will be available or note
            OverLappingList = slots.stream().filter(
                    timeSlot -> !(start_time.isAfter(timeSlot.getEnd_time()) || end_time.isBefore((timeSlot.getStart_time())))
            ).collect(Collectors.toList());

        }
        if (OverLappingList.isEmpty()) return true;
        else return false;

    }

    /**
     * Method allows you to check if an employee is occupied in another meeting within  a given timefram
     *
     * @param request
     * @param employee
     * @return true if employee has another meeting
     * false if there are no other meetings
     */
    public boolean isEmployeeUnAvailable(TimeSlotRequest request, Employee employee) {
        LocalDate date = request.getDate();
        LocalTime start_time = request.getStart_time();
        LocalTime end_time = request.getEnd_time();
        //if an employee is in a meeting in that timeslot
        List<TimeSlot> slots = timeSlotRepository.findAllByDate(date);
        List<Team> teamsOfEmployee = employee.getTeamsList();
        List<TimeSlot> engagedSlots = new ArrayList<>();

        //gives the list of timeslots in which the employee's team is present
        engagedSlots = slots.stream().filter(timeSlot -> timeSlot.getTeamsOfATimeslot().stream().anyMatch(team -> teamsOfEmployee.contains(team))).collect(Collectors.toList());

        //check if timeslot coincides
        List<TimeSlot> OverLappingList = engagedSlots.stream().filter(
                timeSlot -> !(start_time.isAfter(timeSlot.getEnd_time()) || end_time.isBefore((timeSlot.getStart_time())))
        ).collect(Collectors.toList());

        if (OverLappingList.isEmpty()) return false;
        else return true;


    }

    /**
     * Method to cancel a bopked meeting 30mins prior to the meeting's starting time
     *
     * @param timeSlotId
     * @return message according to the operation it does
     */

    @Override
    public CancelMeetingResponse cancelMeeting(int timeSlotId) {

        Optional<TimeSlot> timeSlot = timeSlotRepository.findById(timeSlotId);
        if (timeSlot.isPresent()) {
            if (LocalDate.now().isBefore(timeSlot.get().getDate()) || LocalDate.now().isEqual(timeSlot.get().getDate())) {
                long difference = ChronoUnit.MINUTES.between(LocalTime.now(), timeSlot.get().getStart_time());
                if (difference <= 30) {
                    logger.info("You cannot cancel the meeting,you have less than 30 minutes to start the meeting");
                    CancelMeetingResponse response = new CancelMeetingResponse("You cannot cancel the meeting,you have less than 30 minutes to start the meeting");
                    return response;
                } else {
                    timeSlotRepository.deleteById(timeSlotId);
                    logger.info("deleted the meeting");
                    return new CancelMeetingResponse("Deleted the meeting");
                }

            } else {
                logger.info("Meeting already got over....");
                return new CancelMeetingResponse("Meeting already got over");

            }
        } else {
            logger.info("No such meeting exists");
            return new CancelMeetingResponse("No such meeting exists");

        }


    }


}
