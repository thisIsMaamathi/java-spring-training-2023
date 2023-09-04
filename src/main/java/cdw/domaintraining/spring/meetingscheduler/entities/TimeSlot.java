package cdw.domaintraining.spring.meetingscheduler.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeslot_id")
    private int timeSlotId;
    @Column(name = "team_id")
    private int teamId;
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "meeting_date")
    private LocalDate date;
    @Column(name = "start_time")
    private LocalTime start_time;
    @Column(name = "end_time")
    private LocalTime end_time;
    @Column(name = "description")
    private String desc;
    @Column(name = "booked")
    private int booked;
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "timeslot_teams",
            joinColumns = @JoinColumn(name = "timeslot_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teamsOfATimeslot = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(cascade ={ CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="booked_by")
    private Employee employee;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="timeslot_room",
    joinColumns = @JoinColumn(name="timslot_id"),
    inverseJoinColumns = @JoinColumn(name="room_id"))
    private List<Room> roomsInTimeslot=new ArrayList<>();



    public TimeSlot() {
    }

    public TimeSlot( int teamId, int roomId, LocalDate date, LocalTime start_time, LocalTime end_time, String desc, int booked) {

        this.teamId = teamId;
        this.roomId = roomId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.desc = desc;
        this.booked = booked;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int isBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public List<Team> getTeamsOfATimeslot() {
        return teamsOfATimeslot;
    }

    public void setTeamsOfATimeslot(List<Team> teamsOfATimeslot) {
        this.teamsOfATimeslot = teamsOfATimeslot;
    }

    public int getBooked() {
        return booked;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Room> getRoomsInTimeslot() {
        return roomsInTimeslot;
    }

    public void setRoomsInTimeslot(List<Room> roomsInTimeslot) {
        this.roomsInTimeslot = roomsInTimeslot;
    }

    public void addRoomToTimeSlot(Room room){this.roomsInTimeslot.add(room);}
    public void addTeamToTimeSlot(Team team){this.teamsOfATimeslot.add(team);}

}
