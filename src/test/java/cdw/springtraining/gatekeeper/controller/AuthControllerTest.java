package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.models.LoggingResponse;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.models.RegistrationResponse;
import cdw.springtraining.gatekeeper.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit testing for AuthController
 */
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    AuthController authController;

    @Mock
    AuthenticationService authenticationService;

    /**
     * Unit testing for registerUser
     */
    @Test
    public void testRegisterUser() throws Exception {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        RegistrationResponse registrationResponse=new RegistrationResponse();
        when(authenticationService.register(registrationRequest)).thenReturn(registrationResponse);
        RegistrationResponse response1 = authController.registerUser(registrationRequest).getBody();
        assertEquals(registrationResponse,response1);


    }

    /**
     * Unit testing for userLogin
     */
    @Test
    public void testUserLogin() {

        LoginRequest request = new LoginRequest();
        LoggingResponse loggingResponse=new LoggingResponse();
        when(authenticationService.authenticateUser(request)).thenReturn(loggingResponse);
       LoggingResponse response = authController.userLogin(request).getBody();
        assertEquals(loggingResponse,response);

    }
//
//    /**
//     * Unit testing for logout
//     */
//    @Test
//    public void logout(){
//        when(authenticationService.logoutUser("dfvghbjn")).thenReturn("Logged Out");
//        ResponseEntity response=authController.userLogout("dfvghbjn");
//        assertEquals("Logged Out",response.getBody());
//    }


}
