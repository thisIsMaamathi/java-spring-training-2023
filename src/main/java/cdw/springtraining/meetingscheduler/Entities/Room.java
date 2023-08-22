package cdw.springtraining.meetingscheduler.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private int roomId;

    @Column(name="room_name")
    private String roomName;
    @Column(name="room_capacity")
    private int roomCapacity;


    @ManyToMany(mappedBy = "timeSlotRoomList")
    private List<TimeSlot> roomTimeSlotList;

    public Room(int roomId, String roomName, int roomCapacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomCapacity = roomCapacity;
    }

    Room(){}

    public List<TimeSlot> getRoomTimeSlotList() {
        return roomTimeSlotList;
    }

    public void setRoomTimeSlotList(List<TimeSlot> roomTimeSlotList) {
        this.roomTimeSlotList = roomTimeSlotList;
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


}
