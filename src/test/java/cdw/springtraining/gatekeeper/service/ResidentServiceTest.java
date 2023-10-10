package cdw.springtraining.gatekeeper.service;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {

    @InjectMocks
    ResidentService residentService;

    @Mock
    ResidentRepository residentRepository;
    @Mock
    VisitorRepository visitorRepository;
    @Mock
    GateKeeperRepository gateKeeperRepository;
    @Mock
    BlacklistRepository blacklistRepository;


    @Test
    public void testScheduleVisit(){
        ScheduleRequest request=new ScheduleRequest();
        request.setName("Jack");
        request.setAadhar(123456789L);
        request.setPhone(9876543210L);
        request.setResidentId(10);
        request.setDate(LocalDate.of(2023,3,4));
        request.setAdditionalInformation("desc");

        Resident resident = new Resident();
        resident.setId(1);
        resident.setResidenceNumber(10);
        resident.setActive(true);
        resident.setResidentName("Rose");
        resident.setAadhar(3456789L);
        resident.setPhoneNumber(13245678920L);
        resident.setVisitorsList(new ArrayList<>());
        when(residentRepository.findById(10)).thenReturn(Optional.of(resident));
        when(visitorRepository.existsByAadharAndDateAndHouseNumber(request.getAadhar(),request.getDate(),resident.getResidenceNumber())).thenReturn(false);
        String predefinedPass = "dxfgfcgvbhm";



        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Jack");
        visitor.setAadhar(123456789L);
        visitor.setPhone(9876543210L);
        visitor.setDate(LocalDate.of(2023,3,4));

        visitor.setPass(predefinedPass);
        visitor.setAdditionalInfo("description");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());

        when(visitorRepository.findByAadhar(request.getAadhar())).thenReturn(visitor);
        ScheduleResponse scheduleResponse=new ScheduleResponse();
        scheduleResponse.setResidenceId(10);
        scheduleResponse.setPhone(9876543210L);
        scheduleResponse.setAdditionalInformation("desc");
         scheduleResponse.setPass(predefinedPass);
         scheduleResponse.setName("Jack");
         scheduleResponse.setDate(LocalDate.of(2023,3,4));
         scheduleResponse.setAadhar(123456789L);
         scheduleResponse.setVisitorId(1);

         ScheduleResponse response=residentService.scheduleVisit(request);
         assertEquals(scheduleResponse,response);

    }


    @Test
    public void testCancelVisit(){
        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Jack");
        visitor.setAadhar(123456789L);
        visitor.setPhone(9876543210L);
        visitor.setDate(LocalDate.of(2023,3,4));

        visitor.setPass("dxfcgvhbjn");
        visitor.setAdditionalInfo("description");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());

        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));

        assertTrue(residentService.cancelVisit(1));



    }

    @Test
    public void testBlackListUser(){
        BlackListRequest blackListRequest = new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor");

        String message="Added to blackList";

        String response= residentService.blacklistUser(blackListRequest);
        assertEquals(message,response);

    }

    @Test
    public void testBlackListVisitorError() {
        BlackListRequest blackListRequest = new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor23");

        String message = "Added to blackList";


        assertThrows(RuntimeException.class,()->residentService.blacklistUser(blackListRequest));

    }


}
