package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.ResidentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit testing for Resident Controller
 */

@ExtendWith(MockitoExtension.class)
public class ResidentControllerTests {

    @InjectMocks
    ResidentController residentController;

    @Mock
    ResidentService residentService;

    /**
     * Unit testing for schedule
     */
    @Test
    public void scheduleTest(){
        ScheduleResponse scheduleResponse=new ScheduleResponse();
        ScheduleRequest request=new ScheduleRequest();

        when(residentService.scheduleVisit(request)).thenReturn(scheduleResponse);
        ScheduleResponse response=residentController.schedule(request).getBody();
        assertEquals(scheduleResponse,response);
    }

    /**
     * Unit testing for cancel Visitor
     */

    @Test
    public void testCancelVisitor(){
        Integer id=1;
        when(residentService.cancelVisit(1)).thenReturn("Cancelled this scheduled visit");
        ResponseEntity<String> response = residentController.cancelVisitor(1);
        assertEquals("Cancelled this scheduled visit",response.getBody());

    }

    /**
     * Unit testing for resident blacklist
     */
    @Test
    public void testResidentBlackList(){
        BlackListRequest request=new BlackListRequest();
        when(residentService.blacklistUser(request)).thenReturn("Blacklisted");
        String response=residentController.residentBlacklist(request).getBody();
        assertEquals("Blacklisted",response);

    }

}
