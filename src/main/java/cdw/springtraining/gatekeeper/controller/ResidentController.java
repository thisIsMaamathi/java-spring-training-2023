package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.ResidentApi;
import cdw.springtraining.gatekeeper.models.ScheduleRequest;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    /**
     * Endpoint to schedule a visitor's entry
     *
     * @param scheduleRequest
     * @return Response Entity containing the scheduled visit's information
     */
    @Override
    public ResponseEntity<ScheduleResponse> scheduleVisitorEntry(@RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(residentService.scheduleVisit(scheduleRequest));
    }

    /**
     * Endpoint to cancel a visitors entry
     *
     * @param visitorId
     * @return
     */
    @Override
    public ResponseEntity<Void> cancelVisitor(Integer visitorId) {
        residentService.cancelVisit(visitorId);
        return ResponseEntity.status(204).build();
    }

}
