package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    ApproveRequestRepository approveRequestRepository;


    JwtTokenProvider jwtTokenProvider;


    AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticationService(ApproveRequestRepository approveRequestRepository,JwtTokenProvider jwtTokenProvider) {
        this.approveRequestRepository = approveRequestRepository;

        this.jwtTokenProvider=jwtTokenProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

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

        return token;

    }

    public String logoutUser() {

       return "to be implemented";
    }
}
