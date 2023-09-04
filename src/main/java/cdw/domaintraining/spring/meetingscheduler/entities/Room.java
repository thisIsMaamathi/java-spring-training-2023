package cdw.domaintraining.spring.meetingscheduler.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_Id")
    private int roomId;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "room_capacity")
    private int roomCapacity;
    @JsonManagedReference
    @ManyToMany(mappedBy ="roomsInTimeslot" ,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeSlot> TimeSlotsOfRoom=new ArrayList<>();


    public Room(){}
    public Room(String roomName, int roomCapacity, List<TimeSlot> timeSlotsOfRoom) {
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
        TimeSlotsOfRoom = timeSlotsOfRoom;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public List<TimeSlot> getTimeSlotsOfRoom() {
        return TimeSlotsOfRoom;
    }

    public void setTimeSlotsOfRoom(List<TimeSlot> timeSlotsOfRoom) {
        TimeSlotsOfRoom = timeSlotsOfRoom;
    }

    public void addTimeSlotToRoom(TimeSlot timeSlot){this.TimeSlotsOfRoom.add(timeSlot);}

}
