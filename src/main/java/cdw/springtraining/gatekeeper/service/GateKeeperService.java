package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.Users;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.NoEntriesException;
import cdw.springtraining.gatekeeper.exceptions.UserHasBeenRemovedException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.*;
import cdw.springtraining.gatekeeper.repository.BlackListRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * Service class for all gatekeeper operations
 */

@Service
public class GateKeeperService {

    BlackListRepository blacklistRepository;
    UserRepository userRepository;
    VisitorRepository visitorRepository;

    CustomUserDetailsService customUserDetailsService;

    @Autowired
    public GateKeeperService(BlackListRepository blacklistRepository, UserRepository userRepository, VisitorRepository visitorRepository, CustomUserDetailsService customUserDetailsService) {
        this.blacklistRepository = blacklistRepository;
        this.userRepository = userRepository;
        this.visitorRepository = visitorRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    public boolean isGateKeeperBlacklisted(String username) {
        Optional<Users> user = userRepository.findByUserName(username);

        if (user.isEmpty()) throw new UserNotFoundException(CommonConstants.USER_NOT_FOUND);

        if (blacklistRepository.existsByAadhar(user.get().getAadhar()))
            return true;

        else
            return false;

    }


    public boolean canBlacklist(String role, String userType) {
        boolean flag=true;
        if (role.equalsIgnoreCase("resident")) {
            return userType.equalsIgnoreCase("gatekeeper") || userType.equalsIgnoreCase("visitor");

        } else return role.equalsIgnoreCase("gatekeeper") && userType.equalsIgnoreCase("visitor");

    }

    /**
     * Method to get a list of visitors visiting on a particular day
     *
     * @param date
     * @return List of visitors
     */
    public List<Visitor> getVisitorsList(LocalDate date) {
        String principal = customUserDetailsService.getCurrentUserName();
        if (isGateKeeperBlacklisted(principal))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);

        List<Visitors> visitors = visitorRepository.findByDate(date);
        if (visitors.isEmpty()) throw new NoEntriesException("No visits scheduled for this day " + date);
        return visitors.stream().map(
                visitor -> {
                    Visitor response = new Visitor();
                    response.setVisitorId(visitor.getVisitorId());
                    response.setVisitorName(visitor.getVisitorName());
                    response.setPhoneNumber(visitor.getPhone());
                    response.setIsApproved(visitor.getIsApproved());
                    response.setResidenceId(visitor.getResidenceId());
                    response.setHasCheckedIn(visitor.isHasCheckedIn());
                    return response;
                }
        ).toList();
    }

    /**
     * Method for blacklisting a visitor
     *
     * @param blackListRequest
     * @return String with an appropriate message
     */
    public BlackListResponse blacklistVisitor(BlackListRequest blackListRequest) {
        String principal = customUserDetailsService.getCurrentUserName();
        String role = customUserDetailsService.getCurrentUserRole().toString();
        String principalRole=role.substring(1,role.length()-1);

        if (isGateKeeperBlacklisted(principal))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);

        if (blacklistRepository.existsByAadhar(blackListRequest.getAadhar()))
            throw new BlackListedUserException(CommonConstants.ALREADY_BLACKLISTED);

        if (canBlacklist(principalRole,blackListRequest.getUserType())) {
            Blacklist blacklist = new Blacklist(blackListRequest.getAadhar(), blackListRequest.getUserType(), customUserDetailsService.getCurrentUserName());
            blacklistRepository.save(blacklist);
            BlackListResponse response = new BlackListResponse();
            response.setMessage(CommonConstants.ADDED_TO_BLACKLIST);
            return response;
        }

    else throw new RuntimeException(CommonConstants.USER_TYPE_MISMATCH);

}

    /**
     * Method to approve or reject a visitor with respect to their pass
     *
     * @param visitorId
     * @param pass
     * @return String with appropriate message
     */
    public ApprovedVisitorResponse approveVisitor(Integer visitorId, String pass) {

        String principal = customUserDetailsService.getCurrentUserName();
        if (isGateKeeperBlacklisted(principal))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);

        Visitors visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new UserNotFoundException(CommonConstants.VISITOR_NOT_FOUND));
        if (blacklistRepository.existsByAadhar(visitor.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_VISITOR);

        visitor.setApprovedBy(principal);
        if (pass.equals(visitor.getPass())) {
            visitor.setIsApproved("approved");
            visitorRepository.save(visitor);
            ApprovedVisitorResponse response = new ApprovedVisitorResponse();
            response.setMessage(CommonConstants.APPROVED_VISITOR);
            return response;

        } else {
            visitor.setIsApproved("rejected");
            visitorRepository.save(visitor);
            ApprovedVisitorResponse response = new ApprovedVisitorResponse();
            response.setMessage(CommonConstants.REJECTED_VISITOR);
            return response;

        }
    }

    /**
     * Service to fetch contact details of a resident for the gatekeeper
     * @param userId
     * @return residentGateKeeperResponse
     */

    public ResidentGateKeeperResponse viewResident(Integer userId) {
       Users user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(CommonConstants.USER_NOT_FOUND));
       if(!user.isActive()) throw new UserHasBeenRemovedException(CommonConstants.RESIDENT_WAS_DELETED);
       ResidentGateKeeperResponse response=new ResidentGateKeeperResponse();
       response.setResidenceId(user.getResidenceNumber());
       response.setResidentName(user.getFirstName()+" "+user.getLastName());
       response.setPhoneNumber(user.getPhoneNumber());
       return response;
    }
}
