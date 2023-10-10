package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import cdw.springtraining.gatekeeper.entites.Token;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import cdw.springtraining.gatekeeper.security.JwtAuthenticationFilter;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    ApproveRequestRepository approveRequestRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    public String register(RegistrationRequest request) {
        ApproveRequest approveRequest=new ApproveRequest(request.getUserName(), request.getAadhar(),request.getResidenceId(), request.getPhoneNumber(), request.getPassword(), request.getUserType());
        approveRequestRepository.save(approveRequest);
        return "Appended request";
    }

    public String authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Token jwtToken=new Token(token);
        tokenRepository.save(jwtToken);

        return token;

    }


    public String logoutUser(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        tokenRepository.deleteByToken(token);
        return "Logged out";

    }


}
