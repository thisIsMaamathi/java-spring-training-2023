package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import cdw.springtraining.gatekeeper.entites.Token;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.repository.ApproveRequestRepository;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import cdw.springtraining.gatekeeper.security.JwtAuthenticationFilter;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    /**
     * Method for users to append their registration request
     *
     * @param request
     * @return String with appropriate Message
     */
    public String register(RegistrationRequest request) {
        if (approveRequestRepository.existsByAadhar(request.getAadhar()))
            throw new RuntimeException(CommonConstants.ALREADY_ENTRIED);
        if (approveRequestRepository.existsByUserName(request.getUserName()))
            throw new RuntimeException(CommonConstants.USER_NAME_TAKEN);
        ApproveRequest approveRequest = new ApproveRequest(request.getUserName(), request.getFirstName(), request.getLastName(), request.getMailId(), request.getDob(), request.getGender(), request.getAadhar(), request.getResidenceId(), request.getPhoneNumber(), request.getPassword(), request.getUserType());
        approveRequestRepository.save(approveRequest);
        return CommonConstants.APPENDED_REQUEST;
    }

    /**
     * Method for user to log into the application by entering the right credentials
     * @param request
     * @return String with appropriate message
     */
    public String authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        Token jwtToken = new Token(token);
        tokenRepository.save(jwtToken);
        return token;
    }

    /**
     * Method to logout user
     * @param token
     * @return String with appropriate message
     */
    @Transactional
    public String logoutUser(String token) {
        String target = token.substring(7, token.length());
        tokenRepository.deleteByJwt(target);
        return "Logged out";

    }


}
