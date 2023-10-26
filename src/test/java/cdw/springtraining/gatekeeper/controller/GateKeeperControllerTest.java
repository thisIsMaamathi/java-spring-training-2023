package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.service.GateKeeperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit testing for Gatekeeper controller
 */
@ExtendWith(MockitoExtension.class)
public class GateKeeperControllerTest {

    @InjectMocks
    GateKeeperController gateKeeperController;

    @Mock
    GateKeeperService gateKeeperService;

    /**
     * Unit testing for getVisitorsByDate
     */
    @Test
    public void testGetVisitorsByDate(){
        LocalDate date = LocalDate.of(2023,3,3);
        List<Visitor> visitors=new ArrayList<>();
        when(gateKeeperService.getVisitorsList(date)).thenReturn(visitors);
        ResponseEntity<List<Visitor>> response= gateKeeperController.getVisitorsByDate(date);
        assertEquals(visitors,response.getBody());

    }

    /**
     * Unit testing for gateKeeperBlacklist
     */
    @Test
    public void testGatekeeperBlacklist(){
        BlackListRequest request=new BlackListRequest();
        BlackListResponse blacklistResponse=new BlackListResponse();

        when(gateKeeperService.blacklistVisitor(request)).thenReturn(blacklistResponse);
       BlackListResponse response= gateKeeperController.blacklist(request).getBody();
        assertEquals(blacklistResponse,response);

    }

    /**
     * Unit testing for approveVisistors
     */
    @Test
    public void testApproveVisitor(){
        ApprovedVisitorResponse approvedVisitorResponse=new ApprovedVisitorResponse();
        String pass="vhbjn";
        when(gateKeeperService.approveVisitor(1,pass)).thenReturn(approvedVisitorResponse);
        ApprovedVisitorResponse response=gateKeeperController.approveVisitor(1,pass).getBody();
        assertEquals(approvedVisitorResponse,response);
    }

    /**
     * Unit testing for approveVisistors
     */
    @Test
    public void testViewResident(){
        ResidentGateKeeperResponse residentGateKeeperResponse=new ResidentGateKeeperResponse();

        when(gateKeeperService.viewResident(1)).thenReturn(residentGateKeeperResponse);
  ResidentGateKeeperResponse response=gateKeeperController.getUserGateKeeperView(1).getBody();
        assertEquals(residentGateKeeperResponse,response);
    }





}
