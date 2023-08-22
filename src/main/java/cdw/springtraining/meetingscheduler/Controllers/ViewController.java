package cdw.springtraining.meetingscheduler.Controllers;

import cdw.springtraining.meetingscheduler.Entities.TimeSlot;
import cdw.springtraining.meetingscheduler.Services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ViewController {
    ViewService viewService;
    @Autowired
    public ViewController(ViewService viewService){
        this.viewService=viewService;}

    @GetMapping("/allMeetings")
    public List<TimeSlot> getAllMeeting(){
        return viewService.viewAllMeetings();
    }
    @PostMapping("/allMeetingsByDate")
    public List<TimeSlot> getMeetingByDate(@RequestBody LocalDate date){

        return viewService.viewMeetingByDate(date);

    }


}
