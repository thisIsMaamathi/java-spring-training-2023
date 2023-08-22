package cdw.springtraining.meetingscheduler.Controllers;

import cdw.springtraining.meetingscheduler.Entities.TimeSlotRequest;
import cdw.springtraining.meetingscheduler.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BookingController {
      private BookingService bookingService;

      @Autowired
      public BookingController(BookingService bookingService){
          this.bookingService=bookingService;
      }

      @DeleteMapping("/cancelMeeting/{timeslot}")
     public ResponseEntity<String> deleteMeeting(@PathVariable int timeslot){
          String response=bookingService.cancelMeeting(timeslot);
          return ResponseEntity.ok(response);
     }

     @PostMapping("/bookMeeting/{employeeId}/{teamId}/{roomId}")
     public ResponseEntity<String> bookForTeam(@PathVariable int employeeId, @PathVariable int teamId, @RequestBody TimeSlotRequest timeSlotRequest, @PathVariable int roomId){

          String response=bookingService.bookForTeam(employeeId,teamId,timeSlotRequest,roomId);
          return  ResponseEntity.ok(response);
      }

      @PostMapping("/bookColabMeeting/{employeeId}/{roomId}")
     public ResponseEntity<String> bookForColab(@PathVariable int employeeId, @RequestBody TimeSlotRequest request, @RequestParam ArrayList<Integer> collaborators, @PathVariable int roomId){

        String response=bookingService.bookOutsideTeam(employeeId,collaborators,request,roomId);
        return ResponseEntity.ok(response);

      }


}



