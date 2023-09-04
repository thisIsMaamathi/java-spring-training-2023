package cdw.domaintraining.spring.meetingscheduler.requestentity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookColabMeetingRequest {
    int employeeId;
    int roomId;
    LocalDate date;
    LocalTime start_time;
    LocalTime end_time;
    String desc;

    List<Integer> collaborators=new ArrayList<>();

    public BookColabMeetingRequest(int employeeId, int roomId, LocalDate date, LocalTime start_time, LocalTime end_time, String desc, List<Integer> collaborators) {
        this.employeeId = employeeId;
        this.roomId = roomId;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.desc = desc;
        this.collaborators = collaborators;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public List<Integer> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Integer> collaborators) {
        this.collaborators = collaborators;
    }
}
