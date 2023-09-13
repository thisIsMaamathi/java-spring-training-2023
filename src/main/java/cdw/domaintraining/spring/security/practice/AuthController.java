package cdw.domaintraining.spring.security.practice;

import cdw.domaintraining.spring.security.practice.models.JwtRequest;
import cdw.domaintraining.spring.security.practice.models.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest request){
        String token = authService.login(request);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);

        return ResponseEntity.ok(jwtResponse);
    }
}
