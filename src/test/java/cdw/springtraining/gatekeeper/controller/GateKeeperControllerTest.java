package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.entites.Blacklist;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GateKeeperControllerTest {

    @InjectMocks
    GateKeeperController gateKeeperController;

    @Mock
    GateKeeperService gateKeeperService;

    @Test
    public void testGetVisitorsByDate(){
        LocalDate date = LocalDate.of(2023,3,3);
        List<Visitor> visitors=new ArrayList<>();


        when(gateKeeperService.getVisitorsList(date)).thenReturn((List<Visitor>) visitors);
        ResponseEntity<List<Visitor>> response= gateKeeperController.getVisitorsByDate(date);
        assertEquals(visitors,response.getBody());

    }

    @Test
    public void testGatekeeperBlacklist() throws Exception {
        BlackListRequest request=new BlackListRequest();
        when(gateKeeperService.blacklistVisitor(request)).thenReturn("Added to blacklist");
        String response=gateKeeperController.gatekeeperBlacklist(request).getBody();
        assertEquals("Added to blacklist",response);

    }

    @Test
    public void testApproveVisitor() throws Exception {
        GateKeeperApprovalRequest request=new GateKeeperApprovalRequest();
        when(gateKeeperService.approveVisitor(1,request)).thenReturn("Approved visitor");
        String response=gateKeeperController.approveVisitor(1,request).getBody();
        assertEquals("Approved visitor",response);
    }





}
