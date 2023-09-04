package cdw.domaintraining.spring.meetingscheduler.requestentity;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotRequest {



    private LocalDate date;

    private LocalTime start_time;

    private LocalTime end_time;

    public TimeSlotRequest(LocalDate date, LocalTime start_time, LocalTime end_time) {
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
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
