package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.BlackListRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for resident operations
 */
@Service
public class ResidentService {


    BlackListRepository blacklistRepository;
    UserRepository userRepository;
    VisitorRepository visitorRepository;

    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public ResidentService(BlackListRepository blacklistRepository, UserRepository userRepository, VisitorRepository visitorRepository, CustomUserDetailsService customUserDetailsService) {
        this.blacklistRepository = blacklistRepository;
        this.userRepository = userRepository;
        this.visitorRepository = visitorRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Method for scheduling a visit
     *
     * @param request
     * @return Schedule response containing details about the visit
     */

    public ScheduleResponse scheduleVisit(ScheduleRequest request) {
        String principal = customUserDetailsService.getCurrentUserName();
        Users resident = userRepository.findByUserName(principal).orElseThrow(() -> new UserNotFoundException(CommonConstants.RESIDENT_NOT_FOUND));

        if (blacklistRepository.existsByAadhar(request.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_VISITOR);

      if(visitorRepository.existsByAadharAndDateAndResidenceId(request.getAadhar(),request.getDate(),request.getResidenceId()))
          throw new UserAlreadyExistsException(CommonConstants.VISIT_ALREADY_SCHEDULED);



        Visitors visitor = new Visitors(request.getVisitorName(), request.getDate(), request.getAadhar(), request.getPhone(), request.getAdditionalInformation(),request.getResidenceId());
        visitor.setPass(generatePass());
        visitor.getUsersList().add(resident);
        visitorRepository.save(visitor);

        Optional<Visitors> visitorsOptional = visitorRepository.findByAadharAndDateAndResidenceId(request.getAadhar(), request.getDate(),request.getResidenceId());
        if(visitorsOptional.isEmpty()) throw new RuntimeException(CommonConstants.USER_NOT_FOUND);
        Visitors visitors=visitorsOptional.get();
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setVisitorId(visitors.getVisitorId());
            scheduleResponse.setName(visitors.getVisitorName());
            scheduleResponse.setAadhar(visitors.getAadhar());
            scheduleResponse.setDate(visitors.getDate());
            scheduleResponse.setPhone(visitors.getPhone());
            scheduleResponse.setAdditionalInformation(visitors.getAdditionalInfo());
            scheduleResponse.setResidenceId(visitors.getResidenceId());
            scheduleResponse.setPass(visitors.getPass());
            return scheduleResponse;

    }

    /**
     * Method to genrate a pass
     *
     * @return Pass as a string
     */
    public String generatePass() {
        return UUID.randomUUID().toString();
    }

    /**
     * Method to cancel a visit
     *
     * @param visitorId
     * @return String with appropriate response
     */
    public String cancelVisit(int visitorId) {

        Visitors visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new UserNotFoundException(CommonConstants.VISITOR_NOT_FOUND));
        if(visitor.isHasCheckedIn()) throw new RuntimeException(CommonConstants.VISITOR_ALREADY_ENTERED);
        visitorRepository.delete(visitor);
        return CommonConstants.CANCELLED_VISIT;
    }


}

