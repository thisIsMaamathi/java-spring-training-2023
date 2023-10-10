package cdw.springtraining.gatekeeper.controller;

import cdw.springtraining.gatekeeper.api.VisitorsApi;
import cdw.springtraining.gatekeeper.models.ScheduleResponse;
import cdw.springtraining.gatekeeper.service.VisitorService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class VisitorController implements VisitorsApi {
    VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Override
    public ResponseEntity<ScheduleResponse> getVisitorDetails(@RequestParam String visitorPass) {
        return ResponseEntity.ok(visitorService.getVisitorDetails(visitorPass));
    }

    }
