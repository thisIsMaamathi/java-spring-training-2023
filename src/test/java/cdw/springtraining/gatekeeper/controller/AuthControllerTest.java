package cdw.springtraining.gatekeeper.controller;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    AuthController authController;

    @Mock
    AuthenticationService authenticationService;

    @Test
    public void testRegisterUser() throws Exception {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        Object responseEntityData = new Object();
        when(authenticationService.register(registrationRequest)).thenReturn("Approved");
        ResponseEntity response = authController.registerUser(registrationRequest);
        assertEquals("Approved",response.getBody());


    }

    @Test
    public void testUserLogin() throws Exception {

        LoginRequest request = new LoginRequest();
        Object responseEntityData = new Object();
        when(authenticationService.authenticateUser(request)).thenReturn("passKey");
        ResponseEntity response = authController.userLogin(request);
        assertEquals("passKey",response.getBody());


    }



}
