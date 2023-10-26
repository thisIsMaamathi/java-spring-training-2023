package cdw.springtraining.gatekeeper.service;
import cdw.springtraining.gatekeeper.entites.Roles;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.BlackListRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Unit test for GateKeeper Service
 */
@ExtendWith(MockitoExtension.class)
public class GateKeeperServiceTest {

    @InjectMocks
    GateKeeperService gateKeeperService;
    @Mock
    BlackListRepository blacklistRepository;
    @Mock
    VisitorRepository visitorRepository;
    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    CustomUserDetailsService customUserDetailsService;
    @Mock
    UserRepository userRepository;

    /**
     * Unit test for getVisitorsList
     */

    @Test
    public void testGetVisitorsList(){
        LocalDate date=LocalDate.of(2023,4,3);
        String token="Bearer aDszfxdgcfh";
        String tokenString= "aDszfxdgcfh";

        List<Visitors> visitorList=new ArrayList<>();
        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setVisitorName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setDate(date);
        visitor.setPass("azsxdcfgvh");
        visitor.setAdditionalInfo("description");
        visitor.setResidenceId(10);
        visitor.setHasCheckedIn(true);
        visitor.setIsApproved("approved");
        visitorList.add(visitor);

        Users user =new Users();
        user.setUserName("itsMathi");
        user.setActive(true);

        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");
        when(userRepository.findByUserName("itsMathi")).thenReturn(Optional.of(user));
        when(gateKeeperService.isGateKeeperBlacklisted("itsMathi")).thenReturn(false);

        List<Visitor> visitors=new ArrayList<>();
        Visitor visitorObject=new Visitor();
        visitorObject.setVisitorId(1);
        visitorObject.setVisitorName("Sam");
        visitorObject.setResidenceId(10);
        visitorObject.setHasCheckedIn(true);
        visitorObject.setIsApproved("approved");
        visitors.add(visitorObject);

        when(visitorRepository.findByDate(date)).thenReturn(visitorList);
        List<Visitor> response=gateKeeperService.getVisitorsList(date);
        assertEquals(visitors,response);
    }

    /**
     * Unit test for blacklistVisitor
     */

    @Test
    public void testBlackListVisitor(){
        String token="Bearer aDszfxdgcfh";
        String tokenString= "aDszfxdgcfh";

        Users user =new Users();
        user.setUserName("itsMathi");
        user.setActive(true);

        Roles roles=new Roles();
        roles.setRoleId(1);
        roles.setRoleName("resident");


        BlackListRequest blackListRequest=new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("resident"));

         
        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");
        when(blacklistRepository.existsByAadhar(blackListRequest.getAadhar())).thenReturn(false);
        when(gateKeeperService.canBlacklist("resident",blackListRequest.getUserType())).thenReturn(true);
        when(customUserDetailsService.getCurrentUserRole().toString()).thenReturn(authorities.toString());
        BlackListResponse blackListResponse=new BlackListResponse();
        blackListResponse.setMessage("Added user to blacklist");

        BlackListResponse response= gateKeeperService.blacklistVisitor(blackListRequest);
        assertEquals(blackListResponse,response);

    }

    /**
     * Unit test for exception in blacklistVisitor
     */

    @Test
    public void testBlackListVisitorError() {
        BlackListRequest blackListRequest = new BlackListRequest();
        blackListRequest.setAadhar(435678L);
        blackListRequest.setUserType("visitor");
        Users user =new Users();
        user.setUserName("itsMathi");
        user.setAadhar(435678L);
        user.setActive(true);
        when(blacklistRepository.existsByAadhar(user.getAadhar())).thenReturn(true);
        when(userRepository.findByUserName("itsMathi")).thenReturn(Optional.of(user));
        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");
        when(gateKeeperService.isGateKeeperBlacklisted("itsMathi")).thenReturn(true);
        assertThrows(RuntimeException.class,()->gateKeeperService.blacklistVisitor(blackListRequest));
    }



    /**
     * Unit test for approveVisitor success scenario
     */
    @Test
    public void testApproveVisitor(){
        int visitorId=1;
        String pass="cgvhbjn";
        Users user =new Users();
        user.setUserName("itsMathi");
        user.setActive(true);
        user.setAadhar(1234567890L);

        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");
        when(userRepository.findByUserName("itsMathi")).thenReturn(Optional.of(user));
        when(gateKeeperService.isGateKeeperBlacklisted("itsMathi")).thenReturn(false);


        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setVisitorName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setPhone(35467890L);
        visitor.setDate(LocalDate.of(2023,4,3));
        visitor.setPass(pass);
        visitor.setAdditionalInfo("description");
        visitor.setResidenceId(10);

        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));
        when (blacklistRepository.existsByAadhar(1234567890L)).thenReturn(false);



        ApprovedVisitorResponse approvedVisitorResponse=new ApprovedVisitorResponse();
       approvedVisitorResponse.setMessage("Approved the visitor");

       ApprovedVisitorResponse response= gateKeeperService.approveVisitor(1,pass);
        assertEquals(approvedVisitorResponse,response);

    }

    /**
     * Unit test for approveVisitor success scenario
     */
    @Test
    public void testRejectVisitor(){
        int visitorId=1;
        String pass="cgvhbj";
        Users user =new Users();
        user.setUserName("itsMathi");
        user.setActive(true);
        user.setAadhar(1234567890L);

        when(customUserDetailsService.getCurrentUserName()).thenReturn("itsMathi");
        when(userRepository.findByUserName("itsMathi")).thenReturn(Optional.of(user));
        when(gateKeeperService.isGateKeeperBlacklisted("itsMathi")).thenReturn(false);


        Visitors visitor=new Visitors();
        visitor.setVisitorId(1);
        visitor.setVisitorName("Sam");
        visitor.setAadhar(1234567890L);
        visitor.setPhone(35467890L);
        visitor.setDate(LocalDate.of(2023,4,3));
        visitor.setPass(pass);
        visitor.setAdditionalInfo("description");
        visitor.setResidenceId(10);

        when(visitorRepository.findById(1)).thenReturn(Optional.of(visitor));
        when (blacklistRepository.existsByAadhar(1234567890L)).thenReturn(false);



        ApprovedVisitorResponse approvedVisitorResponse=new ApprovedVisitorResponse();
        approvedVisitorResponse.setMessage("Rejected the visitor");

        ApprovedVisitorResponse response= gateKeeperService.approveVisitor(1,"dsfghj");
        assertEquals(approvedVisitorResponse,response);

    }
    /**
     * Unit test for view resident
     */
    @Test
    public void testViewResident(){
        Users user=new Users();
        user.setUserId(1);
        user.setFirstName("Mathi");
        user.setLastName("Krish");
        user.setResidenceNumber(10);
        user.setPhoneNumber(3546789L);
        user.setActive(true);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        ResidentGateKeeperResponse response=new ResidentGateKeeperResponse();
        response.setResidenceId(user.getResidenceNumber());
        response.setResidentName(user.getFirstName()+" "+user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());

        ResidentGateKeeperResponse response1= gateKeeperService.viewResident(1);
        assertEquals(response,response1);



    }

}
