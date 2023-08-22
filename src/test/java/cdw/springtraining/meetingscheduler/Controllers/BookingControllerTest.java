package cdw.springtraining.meetingscheduler.Controllers;

import cdw.springtraining.meetingscheduler.Entities.TimeSlotRequest;
import cdw.springtraining.meetingscheduler.Services.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {
    @InjectMocks
    BookingController bookingController;

    @Mock
    BookingService bookingService;


    @Test
    public void testDeleteMeeting() {
        int timeSlotId = 123;
        when(bookingService.cancelMeeting(timeSlotId)).thenReturn("Cancelled");
        ResponseEntity<String> response = bookingController.deleteMeeting(timeSlotId);
        assertEquals("Cancelled", response.getBody());


    }

    @Test
    public void testBookMeeting(){
        int employeeId=2001;
        int teamId=101;
        int roomId=2;
        TimeSlotRequest request=new TimeSlotRequest();
        when(bookingService.bookForTeam(employeeId,teamId,request,roomId)).thenReturn("Booked Slot");
        ResponseEntity<String> response=bookingController.bookForTeam(employeeId,teamId,request,roomId);
        assertEquals("Booked Slot",response.getBody());

    }



    @Test
    public void testBookMeetingForColab(){
        int employeeId=2001;
        int teamId=101;
        int roomId=2;
        TimeSlotRequest request=new TimeSlotRequest();
        ArrayList<Integer> employeeList=new ArrayList<>();
        when(bookingService.bookOutsideTeam(employeeId,employeeList,request,roomId)).thenReturn("Booked Slot");
        ResponseEntity<String> response=bookingController.bookForColab(employeeId,request,employeeList,roomId);
        assertEquals("Booked Slot",response.getBody());

    }


}