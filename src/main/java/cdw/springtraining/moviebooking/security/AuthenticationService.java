package cdw.springtraining.moviebooking.security;


import cdw.springtraining.moviebooking.requestbody.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

//
@Service
public class AuthenticationService {


    @Autowired
    CustomUserDetailsService userDetailsService;
   @Autowired
    JwtTokenProvider jwtTokenProvider;

   @Autowired
   AuthenticationManager authenticationManager;

    public String authenticateUser(@RequestBody LoginRequest request) {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;


    }

    public String logoutUser() {
        return "logout to be implemented";
    }
}