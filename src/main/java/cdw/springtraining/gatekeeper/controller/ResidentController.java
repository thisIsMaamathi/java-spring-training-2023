package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.ResidentApi;
import cdw.springtraining.gatekeeper.exceptions.UserNotFoundException;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.repository.BlacklistRepository;
import cdw.springtraining.gatekeeper.repository.GateKeeperRepository;
import cdw.springtraining.gatekeeper.repository.ResidentRepository;
import cdw.springtraining.gatekeeper.repository.VisitorRepository;
import cdw.springtraining.gatekeeper.service.ResidentService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ResidentController implements ResidentApi {
    ResidentService residentService;


    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;

    }

    @Override
    public ResponseEntity<ScheduleResponse> schedule( @RequestBody ScheduleRequest scheduleRequest) throws Exception {
        return ResponseEntity.ok(residentService.scheduleVisit(scheduleRequest));
    }
    @Override
    public ResponseEntity cancelVisitor( Integer visitorId) throws Exception {
     return ResponseEntity.status(204).body(residentService.cancelVisit(visitorId));

    }

    @Override
    public ResponseEntity residentBlacklist(@RequestBody BlackListRequest blackListRequest) {
        return ResponseEntity.ok(residentService.blacklistUser(blackListRequest));
    }


    }
