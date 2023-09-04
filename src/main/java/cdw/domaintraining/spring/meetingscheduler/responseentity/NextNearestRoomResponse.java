package cdw.domaintraining.spring.meetingscheduler.responseentity;

import cdw.domaintraining.spring.meetingscheduler.entities.Room;

import java.util.ArrayList;
import java.util.List;

public class NextNearestRoomResponse {

     Room nearestRoom;

    public NextNearestRoomResponse(Room nearestRoom) {
        this.nearestRoom = nearestRoom;
    }

    public Room getNearestRoom() {
        return nearestRoom;
    }

    public void setNearestRoom(Room nearestRoom) {
        this.nearestRoom = nearestRoom;
    }
}
