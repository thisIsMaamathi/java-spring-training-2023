package cdw.springtraining.gatekeeper.service;
import cdw.springtraining.gatekeeper.entites.Roles;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.models.LoggingResponse;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.models.RegistrationResponse;
import cdw.springtraining.gatekeeper.repository.RolesRepository;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Unit test for authentication service class
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    TokenRepository tokenRepository;

    @Mock
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    UserRepository userRepository;
    @Mock
    RolesRepository rolesRepository;

    @Mock
    PasswordEncoder passwordEncoder;

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
        request.setEmail("Raj@Ram");
        request.setPassword("56778");
        request.setUserType("resident");

        Roles role=new Roles();
        role.setRoleName("resident");
        role.setRoleId(1);

        when(userRepository.existsByAadhar(request.getAadhar())).thenReturn(false);
        when(userRepository.existsByResidenceNumber(10)).thenReturn(false);
        when(rolesRepository.findByRoleName("resident")).thenReturn(role);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded Password");

        RegistrationResponse registrationResponse=new RegistrationResponse();
        registrationResponse.setMessage("Registered successfully");

        RegistrationResponse response= authenticationService.register(request);

        assertEquals(registrationResponse,response);

    }

    /**
     * Unit test case for auhenticateUser
     */
    @Test
    public void testAuthenticateUser(){

        LoginRequest request=new LoginRequest();
        request.setUserName("Ram");
        request.setPassword("1234");

        Users user=new Users();
        user.setUserName("Ram");
        user.setPassword("1234");
        user.setUserType("resident");
        user.setIsApproved("approved");
        user.setActive(true);

        when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.of(user));

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        String mockToken = "mockedToken";
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(mockToken);
        LoggingResponse loggingResponse=new LoggingResponse();
        loggingResponse.setMessage(mockToken);

        LoggingResponse generatedToken = authenticationService.authenticateUser(request);
        assertEquals(loggingResponse, generatedToken);

    }

    /**
     * Unit testcase for logooutUser
     */
    @Test
    public void testlogoutUser(){
        String token="Bearer sxfdcgvh";
        String target="sxfdcgvh";
        LoggingResponse loggingResponse=new LoggingResponse();
        loggingResponse.setMessage("Logged out");

       LoggingResponse response= authenticationService.logoutUser(token);

        assertEquals(loggingResponse,response);


    }




}
