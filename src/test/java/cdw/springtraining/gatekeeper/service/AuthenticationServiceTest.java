package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import cdw.springtraining.gatekeeper.security.JwtAuthenticationFilter;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;

/**
 * Unit test for authentication service class
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    ApproveRequestRepository approveRequestRepository;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    TokenRepository tokenRepository;

    @Mock
    JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * unit testing for register
     */
    @Test
    public void testRegister(){
        RegistrationRequest request=new RegistrationRequest();
        request.setAadhar(13243546L);
        request.setDob(LocalDate.of(2003,7,5));
        request.setPhoneNumber(546789L);
        request.setResidenceId(10);
        request.setGender("Male");
        request.setFirstName("Raj");
        request.setLastName("Ram");
        request.setUserName("RajHere");
        request.setMailId("Raj@Ram");
        request.setPassword("56778");
        request.setUserType("resident");

        when(approveRequestRepository.existsByAadhar(request.getAadhar())).thenReturn(false);
        when(approveRequestRepository.existsByUserName(request.getUserName())).thenReturn(false);

        String response= authenticationService.register(request);
        assertEquals("Appended Request",response);

    }

    /**
     * Unit test case for auhenticateUser
     */
    @Test
    public void testAuthenticateUser(){

        LoginRequest request=new LoginRequest();
        request.setUserName("Ram");
        request.setPassword("1234");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        String mockToken = "mockedToken";
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(mockToken);

        String generatedToken = authenticationService.authenticateUser(request);
        assertEquals(mockToken, generatedToken);

    }

    /**
     * Unit testcase for logooutUser
     */
    @Test
    public void testlogoutUser(){
        String token="Bearer sxfdcgvh";
        String target="sxfdcgvh";

        String response= authenticationService.logoutUser(token);
        assertEquals("Logged out",response);


    }




}
