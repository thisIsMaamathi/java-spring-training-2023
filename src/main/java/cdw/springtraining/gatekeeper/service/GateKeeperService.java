package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.GateKeeper;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.NoEntriesException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.repository.UserRepository;
import cdw.springtraining.gatekeeper.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for all gatekeeper operations
 */

@Service
public class GateKeeperService {
    GateKeeperRepository gateKeeperRepository;
    ResidentRepository residentRepository;
    BlacklistRepository blacklistRepository;
    VisitorRepository visitorRepository;
    JwtTokenProvider jwtTokenProvider;
    UserRepository userRepository;

    @Autowired
    public GateKeeperService(GateKeeperRepository gateKeeperRepository, ResidentRepository residentRepository, BlacklistRepository blacklistRepository, VisitorRepository visitorRepository, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.gateKeeperRepository = gateKeeperRepository;
        this.residentRepository = residentRepository;
        this.blacklistRepository = blacklistRepository;
        this.visitorRepository = visitorRepository;
        this.jwtTokenProvider=jwtTokenProvider;
        this.userRepository=userRepository;
    }

    /**
     * Method to extract a gatekeeper from jwt token
     * @param token
     * @return Gatekeeper
     */
    private GateKeeper getGatekeeperFromRequest(String token) {
        String tokenString=token.substring(7, token.length());
        String userName=jwtTokenProvider.getUsername(tokenString);
        return gateKeeperRepository.findByGatekeeperName(userName);
    }

    /**
     * Method to get a list of visitors visiting on a particular day
     * @param date
     * @param token
     * @return List of visitors
     */
    public List<Visitor> getVisitorsList(LocalDate date,String token) {
        GateKeeper gateKeeper = getGatekeeperFromRequest(token);
        if (blacklistRepository.existsByAadhar(gateKeeper.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_EXCEPTION);
        List<Visitors> visitors = visitorRepository.findByDate(date);
        if (visitors.isEmpty()) throw new NoEntriesException("No visits scheduled for this day " + date);
        return visitors.stream().map(
                visitor -> {
                    Visitor response = new Visitor();
                    response.setVisitorId(visitor.getVisitorId());
                    response.setResidenceId(visitor.getHouseNumber());
                    response.setVisitorName(visitor.getName());
                    return response;
                }
        ).toList();
    }

    /**
     * Method for blacklisting a visitor
     * @param blackListRequest
     * @param token
     * @return String with an appropriate message
     */
    public String blacklistVisitor(BlackListRequest blackListRequest,String token) {
        GateKeeper gateKeeper=getGatekeeperFromRequest(token);
        if(blacklistRepository.existsByAadhar(gateKeeper.getAadhar())) throw new BlackListedUserException(CommonConstants.BLACKLISTED_EXCEPTION);

        if (blackListRequest.getUserType().equalsIgnoreCase("visitor")) {
            Blacklist blacklist = new Blacklist(blackListRequest.getAadhar(), blackListRequest.getUserType());
            blacklistRepository.save(blacklist);
            return CommonConstants.ADDED_TO_BLACKLIST;
        } else
            throw new RuntimeException(CommonConstants.USER_TYPE_MISMATCH);
    }

    /**
     * Method to approve or reject a visitor with respect to their pass
     * @param visitorId
     * @param request
     * @param token
     * @return String with appropriate message
     */
    public String approveVisitor(Integer visitorId, GateKeeperApprovalRequest request,String token) {
        GateKeeper isgateKeeperBlacklisted=getGatekeeperFromRequest(token);
        if(blacklistRepository.existsByAadhar(isgateKeeperBlacklisted.getAadhar())) throw new BlackListedUserException(CommonConstants.BLACKLISTED_EXCEPTION);
        Visitors visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new UserNotFoundException(CommonConstants.VISITOR_NOT_FOUND));
        if(blacklistRepository.existsByAadhar(visitor.getAadhar())) throw new BlackListedUserException(CommonConstants.BLACKLISTED_VISITOR);
        GateKeeper gateKeeper = gateKeeperRepository.findById(request.getGatekeeperId()).orElseThrow(() -> new UserNotFoundException(CommonConstants.GATEKEEPER_NOT_FOUND));
        if(blacklistRepository.existsByAadhar(gateKeeper.getAadhar())) throw new BlackListedUserException(CommonConstants.BLACKLISTED_GATEKEEPER);
        visitor.setGateKeeper(gateKeeper);
        if (request.getPass().equals(visitor.getPass())) {
            visitor.setApproved(true);
            visitorRepository.save(visitor);
            return CommonConstants.APPROVED_VISITOR;
        }
        else
        {
            visitor.setApproved(false);
            visitorRepository.save(visitor);
            return CommonConstants.REJECTED_VISITOR;
        }
    }

}
