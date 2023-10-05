package cdw.springtraining.gatekeeper.service;

import cdw.springtraining.gatekeeper.entites.Blacklist;
import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.entites.Visitors;
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

    public ScheduleResponse scheduleVisit(ScheduleRequest request) throws UserNotFoundException {
        Visitors visitor = new Visitors(request.getName(), request.getDate(), request.getAadhar(), request.getPhone(), request.getAdditionalInformation());
        visitor.setPass(generatePass());
        Resident resident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new UserNotFoundException("Resident not found"));
        visitor.setResident(resident);
        visitor.setHouseNumber(resident.getResidenceNumber());
        visitorRepository.save(visitor);

        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setVisitorId(visitor.getVisitorId());
        scheduleResponse.setName(visitor.getName());
        scheduleResponse.setAadhar(visitor.getAadhar());
        scheduleResponse.setDate(visitor.getDate());
        scheduleResponse.setPhone(visitor.getPhone());
        scheduleResponse.setAdditionalInformation(visitor.getAdditionalInfo());
        scheduleResponse.setPass(visitor.getPass());
        return scheduleResponse;

    }


    public String generatePass() {
        return UUID.randomUUID().toString();
    }


    public boolean cancelVisit(int visitorId) throws Exception {

        Visitors visitor=visitorRepository.findById(visitorId).orElseThrow(()->new UserNotFoundException("Visitor not found"));

            visitorRepository.delete(visitor);
            return true;


    }

    public String blacklistUser(BlackListRequest blackListRequest) {
        Blacklist blacklist=new Blacklist(blackListRequest.getAadhar(), blackListRequest.getUserType());
        blacklistRepository.save(blacklist);

        return "Added to blackList";
    }
}

