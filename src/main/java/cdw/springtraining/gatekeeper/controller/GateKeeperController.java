package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.GatekeeperApi;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.models.ResidentGateKeeperResponse;
import cdw.springtraining.gatekeeper.models.BlackListResponse;
import cdw.springtraining.gatekeeper.models.ApprovedVisitorResponse;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.service.GateKeeperService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for gateKeeper operations
 * Accessible to only GateKeepers
 */
@RestController
public class GateKeeperController implements GatekeeperApi {
    GateKeeperService gateKeeperService;

    @Autowired
    public GateKeeperController(GateKeeperService gateKeeperService) {
        this.gateKeeperService = gateKeeperService;
    }

    /**
     * Endpoint for getting a list of Visitors in a specific date
     *
     * @param date
     * @return Response Entity containing a list of Visitors
     */
    @Override
    public ResponseEntity<List<Visitor>> getVisitorsByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(gateKeeperService.getVisitorsList(date));
    }

    /**
     * Endpoint for blacklisting a visitor
     *
     * @param blackListRequest
     * @return Response entitiy containing a String with appropriate message
     */
    @Override
    public ResponseEntity<BlackListResponse> blacklist(@RequestBody BlackListRequest blackListRequest) {
        return ResponseEntity.ok(gateKeeperService.blacklistVisitor(blackListRequest));
    }

    /**
     * Endpoint for approving of rejecting a visitor
     *
     * @param visitorId
     * @param pass
     * @return Response entitiy containing a String with appropriate message
     */
    @Override
    public ResponseEntity<ApprovedVisitorResponse> approveVisitor(@PathVariable Integer visitorId, @RequestParam String pass) {
        return ResponseEntity.status(200).body(gateKeeperService.approveVisitor(visitorId, pass));
    }

    /**
     * Endpoint for grettinga particular resident for contancting them
     * @param userId  (required)
     * @return  ResidentGateKeeperResponse
     */

    @Override
    public ResponseEntity<ResidentGateKeeperResponse> getUserGateKeeperView(@ApiParam(value = "", required = true) @PathVariable("userId") Integer userId) {
        return ResponseEntity.status(200).body(gateKeeperService.viewResident(userId));
    }


    }