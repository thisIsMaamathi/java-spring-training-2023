package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Roles;
import cdw.springtraining.gatekeeper.entites.Token;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserHasBeenRemovedException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.LoggingResponse;
import cdw.springtraining.gatekeeper.models.LoginRequest;
import cdw.springtraining.gatekeeper.models.RegistrationRequest;
import cdw.springtraining.gatekeeper.models.RegistrationResponse;
import cdw.springtraining.gatekeeper.repository.RolesRepository;
import cdw.springtraining.gatekeeper.repository.TokenRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.security.JwtAuthenticationFilter;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationService {


    JwtTokenProvider jwtTokenProvider;
    AuthenticationManager authenticationManager;
    TokenRepository tokenRepository;

    UserRepository userRepository;

    RolesRepository rolesRepository;
    JwtAuthenticationFilter jwtAuthenticationFilter;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, TokenRepository tokenRepository, UserRepository userRepository, RolesRepository rolesRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.passwordEncoder=passwordEncoder;
    }

    /**
     * Method for users to append their registration request
     *
     * @param request
     * @return String with appropriate Message
     */
    public RegistrationResponse register(RegistrationRequest request) {


        if (userRepository.existsByAadhar(request.getAadhar()))
            throw new UserAlreadyExistsException(CommonConstants.USER_ALREADY_REGISTERED);

        if (request.getUserType().equalsIgnoreCase(CommonConstants.GATEKEEPER) && request.getResidenceId() != 0)
            throw new RuntimeException(CommonConstants.GATEKEPER_CANNOT_HAVE_RESIDENCE_NUMBER);

        if (request.getUserType().equalsIgnoreCase(CommonConstants.RESIDENT)) {

            if (request.getResidenceId() == 0)
                throw new RuntimeException(CommonConstants.RESIDENCE_NUMBER_NOT_MENTIONED);

            if (userRepository.existsByResidenceNumber(request.getResidenceId()))
                throw new UserAlreadyExistsException(CommonConstants.RESIDENCE_HAD_BEEN_REGISTERED);
        }



        Users user = new Users(request.getUserName(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getDob(), request.getGender(), request.getAadhar(), request.getPhoneNumber(), request.getResidenceId(), request.getUserType());
        Roles role = rolesRepository.findByRoleName(request.getUserType());
        user.getRolesList().add(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        RegistrationResponse response = new RegistrationResponse();
        response.setMessage(CommonConstants.REGISTERED_SUCCESSFULLY);
        return response;

    }

    /**
     * Method for user to log into the application by entering the right credentials
     *
     * @param request
     * @return String with appropriate message
     */
    public LoggingResponse authenticateUser(LoginRequest request) {
        Optional<Users> user=userRepository.findByUserName(request.getUserName());
        if(user.isPresent()){
            if(user.get().getIsApproved()==null) throw new RuntimeException(CommonConstants.YET_TO_BE_APPROVED);
            if(!user.get().isActive())   throw new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REMOVED);
            if(!user.get().getIsApproved().equals(CommonConstants.APPROVED)) throw  new UserHasBeenRemovedException(CommonConstants.USER_HAD_BEEN_REJECTED+" "+user.get().getApprovedBy());
        }
        else{
            throw new UserNotFoundException(CommonConstants.USER_NOT_FOUND);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        Token jwtToken = new Token(token);
        tokenRepository.save(jwtToken);

        LoggingResponse response = new LoggingResponse();
        response.setMessage(token);
        return response;
    }

    /**
     * Method to logout user
     * @param token
     * @return String with appropriate message
     */
    @Transactional
    public LoggingResponse logoutUser(String token) {
        String target = token.substring(7, token.length());
        tokenRepository.deleteByJwt(target);
        LoggingResponse response=new LoggingResponse();
        response.setMessage(CommonConstants.LOGGED_OUT);
        return response;

    }


}
