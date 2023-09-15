package cdw.springtraining.moviebooking.security;

import cdw.springtraining.moviebooking.requestbody.LoginRequest;
import cdw.springtraining.moviebooking.responseobjects.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/security")
public class LoginController {

    private LoginService loginService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest request){
        String token = loginService.login(request);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);

        return ResponseEntity.ok(jwtResponse);
    }




}