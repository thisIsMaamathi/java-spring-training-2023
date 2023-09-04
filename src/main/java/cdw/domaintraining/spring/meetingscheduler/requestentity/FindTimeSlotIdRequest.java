package cdw.domaintraining.spring.meetingscheduler.requestentity;

import java.time.LocalDate;
import java.time.LocalTime;

public class FindTimeSlotIdRequest {

     int roomId;
     LocalDate date;
     LocalTime start_time;
     LocalTime end_time;

    public FindTimeSlotIdRequest(int roomId, LocalDate date, LocalTime start_time, LocalTime end_time) {
        this.roomId = roomId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
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
}
