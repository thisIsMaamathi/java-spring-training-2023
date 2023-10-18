package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.VisitorsApi;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Visitor operations
 */
@RestController
public class VisitorController implements VisitorsApi {
    VisitorService visitorService;
    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    /**
     * Endpoint for viewing a scheduled visitor
     * @param visitorPass
     * @return Response entity containing a Schedule Response with details related to the visit
     */
    @Override
    public ResponseEntity<ScheduleResponse> getVisitorDetails(@RequestParam String visitorPass) {
        return ResponseEntity.ok(visitorService.getVisitorDetails(visitorPass));
    }

    }
