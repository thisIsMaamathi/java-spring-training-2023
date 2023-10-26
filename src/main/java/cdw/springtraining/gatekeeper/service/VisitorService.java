package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.constant.CommonConstants;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for visitor operations
 */
@Service
public class VisitorService {
    VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    /**
     * Method to view  visitor details
     *
     * @param visitorPass
     * @return Schedule Response with details regarding the visit
     */
    public ScheduleResponse getVisitorDetails(String visitorPass) {
        Visitors visitor = visitorRepository.findByPass(visitorPass);
        if (visitor != null) {
            ScheduleResponse response = new ScheduleResponse();
            response.setVisitorId(visitor.getVisitorId());
            response.setName(visitor.getVisitorName());
            response.setDate(visitor.getDate());
            response.setAadhar(visitor.getAadhar());
            response.setAdditionalInformation(visitor.getAdditionalInfo());
            response.setPhone(visitor.getPhone());
            response.setPass(visitor.getPass());
            response.setResidenceId(visitor.getResidenceId());
            return response;

        } else
            throw new UserNotFoundException(CommonConstants.VISITOR_NOT_FOUND);
    }
}
