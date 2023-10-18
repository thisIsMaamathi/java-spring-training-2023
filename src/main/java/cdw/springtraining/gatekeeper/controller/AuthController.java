
package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.AuthApi;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Authentication,registration and logout
 * These are open endpoints, can be accessed by anyone
 */
@RestController
public class AuthController implements AuthApi {
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Endpoint for the user to send request for approval
     * @param registrationRequest
     * @return String containing appropriate message
     */
    @Override
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.status(200).body(authenticationService.register(registrationRequest));
    }

    /**
     * Endpoint for user login
     * @param request
     * @return Jwt token for user
     */
    @Override
    public ResponseEntity<String> userLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateUser(request));
    }

    /**
     * Endpoint for user Logout
     * @param token
     * @return String with appropriate message
     */
    @Override
    public ResponseEntity<String> userLogout(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(authenticationService.logoutUser(token));
    }

}

