package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitorControllerTest {

    @InjectMocks
    VisitorController visitorController;
    @Mock
    VisitorService visitorService;

    @Test
    public void testGetVisitorPass(){
        ScheduleResponse scheduleResponse=new ScheduleResponse();
        String pass="abc";
        when(visitorService.getVisitorDetails(pass)).thenReturn(scheduleResponse);
        ScheduleResponse response=visitorController.getVisitorDetails(pass).getBody();
        assertEquals(scheduleResponse,response);
    }
}
