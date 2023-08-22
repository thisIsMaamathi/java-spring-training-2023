package cdw.springtraining.meetingscheduler.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="time_slot")
public class TimeSlot {

          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          @Column(name="time_slot_id")
         private int timeSlotId;

         @Column(name="team_id")
         private int teamId;

         @Column(name="room_id")
         private int roomId;

         @Column(name="date")
         private LocalDate date;

        @Column(name="start_time")
        private LocalTime startTime;
       @Column(name="end_time")
        private LocalTime endTime;

        @Column(name="booked")
        private int booked;

        @Column(name="description")
        private String description;

        @ManyToOne
        @JoinColumn(name="timeslot_employee")
        private Employee employee;
        @ManyToMany
        @JoinTable(name="timeslot_team",
                joinColumns= @JoinColumn(name = "time_slot_id"),
                inverseJoinColumns = @JoinColumn(name = "team_id"))
        private List<Teams> timeSlotTeamList=new ArrayList<>();


    @ManyToMany
    @JoinTable(name="timeslot_room",
            joinColumns= @JoinColumn(name = "time_slot_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> timeSlotRoomList=new ArrayList<>();






    public TimeSlot(int timeSlotId, int teamId, int roomId, LocalDate date, LocalTime startTime, LocalTime endTime, int booked, String description) {
        this.timeSlotId = timeSlotId;
        this.teamId = teamId;
        this.roomId = roomId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.booked = booked;
        this.description = description;
    }

    TimeSlot(){}

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Teams> getTimeSlotTeamList() {
        return timeSlotTeamList;
    }

    public void setTimeSlotTeamList(List<Teams> timeSlotTeamList) {
        this.timeSlotTeamList = timeSlotTeamList;
    }

    public List<Room> getTimeSlotRoomList() {
        return timeSlotRoomList;
    }

    public void setTimeSlotRoomList(List<Room> timeSlotRoomList) {
        this.timeSlotRoomList = timeSlotRoomList;
    }

    public void addTeamtoTimeSlot(Teams teams){
        this.timeSlotTeamList.add(teams);
    }

    public void addRoomtoTimeSlot(Room room){
        this.timeSlotRoomList.add(room);
    }



}
