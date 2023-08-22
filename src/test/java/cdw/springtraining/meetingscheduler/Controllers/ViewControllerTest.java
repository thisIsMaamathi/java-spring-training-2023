package cdw.springtraining.meetingscheduler.Controllers;

import cdw.springtraining.meetingscheduler.Entities.TimeSlotRequest;
import cdw.springtraining.meetingscheduler.Services.BookingService;
import cdw.springtraining.meetingscheduler.Services.ViewService;
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
public class ViewControllerTest {
    @InjectMocks
    ViewController viewController;

    @Mock
    ViewService viewService;



}