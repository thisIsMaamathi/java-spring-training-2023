package cdw.springtraining.moviebooking.security;

import cdw.springtraining.moviebooking.requestbody.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateUser(request));

    }

   @PostMapping("/logout")
    public ResponseEntity<String>logout(){
        return ResponseEntity.ok(authenticationService.logoutUser());
   }
}
