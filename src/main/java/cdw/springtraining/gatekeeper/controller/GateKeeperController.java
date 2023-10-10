package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.GateKeeperApi;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.service.GateKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class GateKeeperController implements GateKeeperApi {

    GateKeeperService gateKeeperService;

    @Autowired
    public GateKeeperController(GateKeeperService gateKeeperService) {
        this.gateKeeperService = gateKeeperService;
    }

    @Override
    public ResponseEntity<List<Visitor>> getVisitorsByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(gateKeeperService.getVisitorsList(date));

    }

    @Override
    public ResponseEntity<String> gatekeeperBlacklist(@RequestBody BlackListRequest blackListRequest) {
        return ResponseEntity.ok(gateKeeperService.blacklistVisitor(blackListRequest));
    }

    @Override
    public ResponseEntity<String> approveVisitor(@PathVariable Integer visitorId, @RequestBody GateKeeperApprovalRequest request) {
        return ResponseEntity.ok(gateKeeperService.approveVisitor(visitorId, request));
    }
}