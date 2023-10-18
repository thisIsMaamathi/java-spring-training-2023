package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.BlackListedUserException;
import cdw.springtraining.gatekeeper.exceptions.UserAlreadyExistsException;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service class for resident operations
 */
@Service
public class ResidentService {

    ResidentRepository residentRepository;
    VisitorRepository visitorRepository;
    GateKeeperRepository gateKeeperRepository;
    BlacklistRepository blacklistRepository;

    @Autowired
    public ResidentService(ResidentRepository residentRepository, VisitorRepository visitorRepository, GateKeeperRepository gateKeeperRepository, BlacklistRepository blacklistRepository) {
        this.residentRepository = residentRepository;
        this.visitorRepository = visitorRepository;
        this.gateKeeperRepository = gateKeeperRepository;
        this.blacklistRepository = blacklistRepository;
    }

    /**
     * Method for scheduling a visit
     *
     * @param request
     * @return Schedule response containing details about the visit
     */

    public ScheduleResponse scheduleVisit(ScheduleRequest request) {
        Resident resident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new UserNotFoundException(CommonConstants.RESIDENT_NOT_FOUND));
        if (blacklistRepository.existsByAadhar(request.getAadhar()))
            throw new BlackListedUserException(CommonConstants.BLACKLISTED_VISITOR);
        if (visitorRepository.existsByAadharAndDateAndHouseNumber(request.getAadhar(), request.getDate(), resident.getResidenceNumber()))
            throw new UserAlreadyExistsException(CommonConstants.VISIT_ALREADY_SCHEDULED);

        Visitors visitor = new Visitors(request.getName(), request.getDate(), request.getAadhar(), request.getPhone(), request.getAdditionalInformation());
        visitor.setPass(generatePass());
        visitor.setResident(resident);
        visitor.setHouseNumber(resident.getResidenceNumber());
        visitorRepository.save(visitor);

        Visitors visitors = visitorRepository.findByAadhar(request.getAadhar());
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setVisitorId(visitors.getVisitorId());
        scheduleResponse.setName(visitor.getName());
        scheduleResponse.setAadhar(visitor.getAadhar());
        scheduleResponse.setDate(visitor.getDate());
        scheduleResponse.setPhone(visitor.getPhone());
        scheduleResponse.setAdditionalInformation(visitor.getAdditionalInfo());
        scheduleResponse.setResidenceId(resident.getResidenceNumber());
        scheduleResponse.setPass(visitors.getPass());
        return scheduleResponse;

    }

    /**
     * Method to genrate a pass
     * @return Pass as a string
     */
    public String generatePass() {
        return UUID.randomUUID().toString();
    }

    /**
     * Method to cancel a visit
     * @param visitorId
     * @return  String with appropriate response
     */
    public String cancelVisit(int visitorId) {
        Visitors visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new UserNotFoundException(CommonConstants.VISITOR_NOT_FOUND));
        visitorRepository.delete(visitor);
        return CommonConstants.CANCELLED_VISIT;
    }

    /**
     * Method to blacklist a user
     * @param blackListRequest
     * @return String with appropriate messagex
     */
    public String blacklistUser(BlackListRequest blackListRequest) {
        if (blackListRequest.getUserType().equalsIgnoreCase("visitor") || blackListRequest.getUserType().equalsIgnoreCase("gatekeeper")) {
            Blacklist blacklist = new Blacklist(blackListRequest.getAadhar(), blackListRequest.getUserType());
            blacklistRepository.save(blacklist);
            return CommonConstants.ADDED_TO_BLACKLIST;
        } else
            throw new UserNotFoundException(CommonConstants.USER_TYPE_MISMATCH);
    }
}

