package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.GateKeeper;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GateKeeperServiceTest {

    @InjectMocks
    GateKeeperService gateKeeperService;
    @Mock
    GateKeeperRepository gateKeeperRepository;
    @Mock
    ResidentRepository residentRepository;
    @Mock
    BlacklistRepository blacklistRepository;
    @Mock
    VisitorRepository visitorRepository;

    @Test
    public void testGetVisitorsList(){
        LocalDate date=LocalDate.of(2023,4,3);

        List<Visitors> visitorList=new ArrayList<>();
        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setPhone(35467890L);
        visitor.setDate(date);
        visitor.setPass("azsxdcfgvh");
        visitor.setAdditionalInfo("description");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());
        visitorList.add(visitor);

        List<Visitor> visitors=new ArrayList<>();
        Visitor visitorObject=new Visitor();
        visitorObject.setVisitorId(1);
        visitorObject.setVisitorName("Sam");
        visitorObject.setResidenceId(10);

        visitors.add(visitorObject);

        when(visitorRepository.findByDate(date)).thenReturn(visitorList);

        List<Visitor> response=gateKeeperService.getVisitorsList(date);
        assertEquals(visitors,response);



    }

    @Test
    public void testBlackListVisitor(){
        BlackListRequest blackListRequest=new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor");

        String message="Added to blackList";

        String response= gateKeeperService.blacklistVisitor(blackListRequest);
        assertEquals(message,response);

    }

    @Test
    public void testBlackListVisitorError() {
        BlackListRequest blackListRequest = new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor23");

        String message = "Added to blackList";


        assertThrows(RuntimeException.class,()->gateKeeperService.blacklistVisitor(blackListRequest));

    }
        @Test
    public void testApproveVisitor(){
        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setPhone(35467890L);
        visitor.setDate(LocalDate.of(2023,4,3));
        visitor.setPass("azsxdcfgvh");
        visitor.setAdditionalInfo("description");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());


        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeper_name("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));
        when(gateKeeperRepository.findById(1)).thenReturn(Optional.of(gateKeeper));

        GateKeeperApprovalRequest approvalRequest=new GateKeeperApprovalRequest();
        approvalRequest.setPass("azsxdcfgvh");
        approvalRequest.setGatekeeperId(1);

        String response= gateKeeperService.approveVisitor(1,approvalRequest);
        assertEquals("Approved the visitor",response);

    }

    @Test
    public void testRejectVisitor(){
        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setPhone(35467890L);
        visitor.setDate(LocalDate.of(2023,4,3));
        visitor.setPass("azsxdcfgvh");
        visitor.setAdditionalInfo("description");
        visitor.setHouseNumber(10);
        visitor.setResident(new Resident());


        GateKeeper gateKeeper = new GateKeeper();
        gateKeeper.setGatekeeper_id(1);
        gateKeeper.setGateId(1);
        gateKeeper.setActive(true);
        gateKeeper.setGatekeeper_name("Babu");
        gateKeeper.setAadhar(3456789L);
        gateKeeper.setPhone_number(1324567890L);
        gateKeeper.setVisitorsList(new ArrayList<>());

        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));
        when(gateKeeperRepository.findById(1)).thenReturn(Optional.of(gateKeeper));

        GateKeeperApprovalRequest approvalRequest=new GateKeeperApprovalRequest();
        approvalRequest.setPass("azsxdcfgvkjch");
        approvalRequest.setGatekeeperId(1);

        String response= gateKeeperService.approveVisitor(1,approvalRequest);
        assertEquals("Rejected the visitor",response);

    }




}
