package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.ResidentApi;
import cdw.springtraining.gatekeeper.models.BlackListRequest;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all Resident operations
 * Only Residents can access these endpoints
 */
@RestController
public class ResidentController implements ResidentApi {
    ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    /**
     * Endpoint to schedule a visitor's entry
     * @param scheduleRequest
     * @return Response Entity containing the scheduled visit's information
     */
    @Override
    public ResponseEntity<ScheduleResponse> schedule( @RequestBody ScheduleRequest scheduleRequest)  {
        return ResponseEntity.ok(residentService.scheduleVisit(scheduleRequest));
    }

    /**
     * Endpoint to cancel a visitors entry
     * @param visitorId
     * @return
     */
    @Override
    public ResponseEntity<String> cancelVisitor( Integer visitorId)  {
     return ResponseEntity.status(204).body(residentService.cancelVisit(visitorId));
    }

    /**
     * Endpoint to blacklist a visitor or a gatekeeper
     * @param blackListRequest
     * @return String with appropriate response
     */
    @Override
    public ResponseEntity<String> residentBlacklist(@RequestBody BlackListRequest blackListRequest)  {
        return ResponseEntity.ok(residentService.blacklistUser(blackListRequest));
    }


    }
