package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
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
        String token="vhbjn";


        when(gateKeeperService.getVisitorsList(date,token)).thenReturn((List<Visitor>) visitors);
        ResponseEntity<List<Visitor>> response= gateKeeperController.getVisitorsByDate(date,token);
        assertEquals(visitors,response.getBody());

    }

    /**
     * Unit testing for gateKeeperBlacklist
     */
    @Test
    public void testGatekeeperBlacklist(){
        BlackListRequest request=new BlackListRequest();
        String token="vhbjn";
        when(gateKeeperService.blacklistVisitor(request,token)).thenReturn("Added to blacklist");
        String response=gateKeeperController.gatekeeperBlacklist(token,request).getBody();
        assertEquals("Added to blacklist",response);

    }

    /**
     * Unit testing for approveVisistors
     */
    @Test
    public void testApproveVisitor(){
        GateKeeperApprovalRequest request=new GateKeeperApprovalRequest();
        String token="vhbjn";
        when(gateKeeperService.approveVisitor(1,request,token)).thenReturn("Approved visitor");
        String response=gateKeeperController.approveVisitor(1,token,request).getBody();
        assertEquals("Approved visitor",response);
    }





}
