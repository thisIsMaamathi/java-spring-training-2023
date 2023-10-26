package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.BlackListResponse;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.BlackListRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit tes for Resident service
 */
@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {
    @InjectMocks
    ResidentService residentService;

    @Mock
    VisitorRepository visitorRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BlackListRepository blackListRepository;

    @Mock
    CustomUserDetailsService customUserDetailsService;


    /**
     * Unit test for scheduleVisit
     */
    @Test
    public void testScheduleVisit() {
        ScheduleRequest request = new ScheduleRequest();
        request.setVisitorName("Jack");
        request.setAadhar(123456789L);
        request.setPhone(9876543210L);
        request.setResidenceId(10);
        request.setDate(LocalDate.of(2023, 3, 4));
        request.setAdditionalInformation("description");

        Users resident = new Users();
        resident.setUserId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setUserName("Rose");


        when(blackListRepository.existsByAadhar(request.getAadhar())).thenReturn(false);
        when(customUserDetailsService.getCurrentUserName()).thenReturn("Rose");
        when(userRepository.findByUserName("Rose")).thenReturn(Optional.of(resident));
        when(visitorRepository.existsByAadharAndDateAndResidenceId(request.getAadhar(), request.getDate(), resident.getResidenceNumber())).thenReturn(false);
        String predefinedPass = "dxfgfcgvbhm";


        Visitors visitor = new Visitors();
        visitor.setVisitorId(1);
        visitor.setVisitorName("Jack");
        visitor.setAadhar(123456789L);
        visitor.setPhone(9876543210L);
        visitor.setDate(LocalDate.of(2023, 3, 4));

        visitor.setPass(predefinedPass);
        visitor.setAdditionalInfo("description");
        visitor.setResidenceId(10);


        when(visitorRepository.findByAadharAndDateAndResidenceId(request.getAadhar(),request.getDate(),request.getResidenceId())).thenReturn(Optional.of(visitor));
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setResidenceId(10);
        scheduleResponse.setPhone(9876543210L);
        scheduleResponse.setAdditionalInformation("description");
        scheduleResponse.setPass(predefinedPass);
        scheduleResponse.setName("Jack");
        scheduleResponse.setDate(LocalDate.of(2023, 3, 4));
        scheduleResponse.setAadhar(123456789L);
        scheduleResponse.setVisitorId(1);

        ScheduleResponse response = residentService.scheduleVisit(request);
        assertEquals(scheduleResponse, response);

    }

    /**
     * unit test for cancelVisit
     */
    @Test
    public void testCancelVisit() {
        Visitors visitor = new Visitors();
        visitor.setVisitorId(1);
        visitor.setVisitorName("Jack");
        visitor.setAadhar(123456789L);
        visitor.setPhone(9876543210L);
        visitor.setDate(LocalDate.of(2023, 3, 4));

        visitor.setPass("dxfcgvhbjn");
        visitor.setAdditionalInfo("description");
        visitor.setResidenceId(10);


        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));
        String response=residentService.cancelVisit(visitor.getVisitorId());
        assertEquals("Cancelled this scheduled visit",response);



    }




}
