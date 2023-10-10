package cdw.springtraining.gatekeeper.service;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceTest {

    @InjectMocks
    VisitorService visitorService;

    @Mock
    VisitorRepository visitorRepository;

    @Test
    public void testGetVisitorDetails(){
        ScheduleResponse scheduleResponse=new ScheduleResponse();
        scheduleResponse.setVisitorId(1);
        scheduleResponse.setPass("aDszfxgchv");
        scheduleResponse.setName("Jack");
        scheduleResponse.setDate(LocalDate.of(2023,3,4));
        scheduleResponse.setAadhar(123456789L);
        scheduleResponse.setPhone(9876543210L);
        scheduleResponse.setResidenceId(10);
        scheduleResponse.setAdditionalInformation("desc");

        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Jack");
        visitor.setAadhar(123456789L);
        visitor.setPhone(9876543210L);
        visitor.setDate(LocalDate.of(2023,3,4));
        visitor.setPass("aDszfxgchv");
        visitor.setAdditionalInfo("desc");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());

        when(visitorRepository.findByPass("aDszfxgchv")).thenReturn(visitor);
        ScheduleResponse response= visitorService.getVisitorDetails("aDszfxgchv");
        assertEquals(scheduleResponse,response);

    }

}
