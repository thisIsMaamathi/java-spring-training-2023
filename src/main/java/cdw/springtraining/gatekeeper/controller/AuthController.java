package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.AuthApi;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.security.JwtAuthenticationFilter;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import cdw.springtraining.gatekeeper.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    HttpServletRequest request;
    HttpServletResponse response;
    Authentication authentication;


    JwtTokenProvider jwtTokenProvider;




    @Override
    public ResponseEntity registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.status(200).body(authenticationService.register(registrationRequest));

    }

    @Override
    public ResponseEntity<String> userLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateUser(request));

    }
    @Override
    public ResponseEntity<String> userLogout(){
        return ResponseEntity.ok(authenticationService.logoutUser(request));
    }






}
