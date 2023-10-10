package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.GateKeeper;
import cdw.springtraining.gatekeeper.entites.Visitors;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GateKeeperService {

    GateKeeperRepository gateKeeperRepository;
    ResidentRepository residentRepository;
    BlacklistRepository blacklistRepository;
    VisitorRepository visitorRepository;

    @Autowired
    public GateKeeperService(GateKeeperRepository gateKeeperRepository, ResidentRepository residentRepository, BlacklistRepository blacklistRepository, VisitorRepository visitorRepository) {
        this.gateKeeperRepository = gateKeeperRepository;
        this.residentRepository = residentRepository;
        this.blacklistRepository = blacklistRepository;
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getVisitorsList(LocalDate date) {
        List<Visitors> visitors = visitorRepository.findByDate(date);
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

    public String blacklistVisitor(BlackListRequest blackListRequest) {

        if (blackListRequest.getUserType().equalsIgnoreCase("visitor")) {
            Blacklist blacklist = new Blacklist(blackListRequest.getAadhar(), blackListRequest.getUserType());
            blacklistRepository.save(blacklist);
            return "Added to blackList";
        } else
            throw new RuntimeException("Enter a valid usertype");
    }


    public String approveVisitor(Integer visitorId, GateKeeperApprovalRequest request) {


        Visitors visitor = visitorRepository.findById(visitorId).orElseThrow(() -> new UserNotFoundException("No such visitor present"));
        GateKeeper gateKeeper = gateKeeperRepository.findById(request.getGatekeeperId()).orElseThrow(() -> new UserNotFoundException("No such gatekeeper present"));
        visitor.setGateKeeper(gateKeeper);
        if (request.getPass().equals(visitor.getPass())) {
            visitor.setApproved(true);
            visitorRepository.save(visitor);
            return "Approved the visitor";

        } else {
            visitor.setApproved(false);
            visitorRepository.save(visitor);
            return "Rejected the visitor";
        }
    }
}
