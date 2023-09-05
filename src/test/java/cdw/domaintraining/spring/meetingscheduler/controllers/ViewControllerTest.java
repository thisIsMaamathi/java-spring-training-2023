package cdw.domaintraining.spring.meetingscheduler.controllers;

import cdw.domaintraining.spring.meetingscheduler.controllerimpl.ViewController;
import cdw.domaintraining.spring.meetingscheduler.entities.TimeSlot;
import cdw.domaintraining.spring.meetingscheduler.responseentity.ViewMeetingsResponse;
import cdw.domaintraining.spring.meetingscheduler.serviceimpl.ViewServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViewControllerTest {
    @InjectMocks
    ViewController viewController;

    @Mock
    ViewServices viewServices;

    @Test
    public void showAllMeetingsTest() throws Exception {
        TimeSlot timeSlot1 = new TimeSlot(101, 1, LocalDate.of(2023, 1, 4), LocalTime.of(12, 0), LocalTime.of(12, 30), "casual", 1);
        TimeSlot timeSlot2 = new TimeSlot(100, 3, LocalDate.of(2023, 5, 6), LocalTime.of(13, 00), LocalTime.of(13, 30), "connect", 1);

        List<TimeSlot> meetings = new ArrayList<>();
        meetings.add(timeSlot1);
        meetings.add(timeSlot2);

        ViewMeetingsResponse viewMeetingsResponse = new ViewMeetingsResponse(meetings);
        ResponseEntity<ViewMeetingsResponse> mockEntity = ResponseEntity.ok(viewMeetingsResponse);

        when(viewServices.findAllMeetings()).thenReturn(viewMeetingsResponse);

        ResponseEntity<ViewMeetingsResponse> response = viewController.
                findAllMeetings();
        assertEquals(mockEntity, response);
    }


}
