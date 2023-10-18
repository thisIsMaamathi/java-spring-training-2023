package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.GateKeeperApi;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.GateKeeperApprovalRequest;
import cdw.springtraining.gatekeeper.models.Visitor;
import cdw.springtraining.gatekeeper.service.GateKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for gateKeeper operations
 * Accessible to only GateKeepers
 */
@RestController
public class GateKeeperController implements GateKeeperApi {
    GateKeeperService gateKeeperService;
    @Autowired
    public GateKeeperController(GateKeeperService gateKeeperService) {
        this.gateKeeperService = gateKeeperService;
    }

    /**
     * Endpoint for getting a list of Visitors in a specific date
     * @param date
     * @param token
     * @return Response Entity containing a list of Visitors
     */
    @Override
    public ResponseEntity<List<Visitor>> getVisitorsByDate(@RequestParam LocalDate date, @RequestHeader(name = "Authorization") String token){
        return ResponseEntity.ok(gateKeeperService.getVisitorsList(date,token));
    }

    /**
     * Endpoint for blacklisting a visitor
     * @param token
     * @param blackListRequest
     * @return Response entitiy containing a String with appropriate message
     */
    @Override
    public ResponseEntity<String> gatekeeperBlacklist(@RequestHeader(name = "Authorization") String token,@RequestBody BlackListRequest blackListRequest) {
        return ResponseEntity.ok(gateKeeperService.blacklistVisitor(blackListRequest,token));
    }

    /**
     * Endpoint for approving of rejecting a visitor
     * @param visitorId
     * @param token
     * @param request
     * @return Response entitiy containing a String with appropriate message
     */
    @Override
    public ResponseEntity<String> approveVisitor(@PathVariable Integer visitorId, @RequestHeader(name = "Authorization") String token,@RequestBody GateKeeperApprovalRequest request) {
        return ResponseEntity.ok(gateKeeperService.approveVisitor(visitorId, request,token));
    }


}