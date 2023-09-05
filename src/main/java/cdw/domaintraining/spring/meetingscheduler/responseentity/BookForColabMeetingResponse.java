package cdw.domaintraining.spring.meetingscheduler.responseentity;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookForColabMeetingResponse {
    int roomId;
    int teamId;
    LocalDate date;
    LocalTime start_time;
    LocalTime end_time;
    String desc;


    public BookForColabMeetingResponse(int roomId, int teamId, LocalDate date, LocalTime start_time, LocalTime end_time, String desc) {
        this.roomId = roomId;
        this.teamId = teamId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.desc = desc;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
}
