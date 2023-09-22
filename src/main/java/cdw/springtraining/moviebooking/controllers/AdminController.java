package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.entity.Location;
import cdw.springtraining.moviebooking.requestbody.AddLocationRequest;
import cdw.springtraining.moviebooking.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService adminservice;

    public AdminController(AdminService adminservice) {
        this.adminservice = adminservice;
    }

    @PostMapping("/location")
    public ResponseEntity<Location> addALocation(@RequestBody AddLocationRequest request){
        return ResponseEntity.ok(adminservice.addLocation(request));
    }

    @DeleteMapping("/location/{location_id}")
    public ResponseEntity<String> removeALocation(@PathVariable int location_id){
        return ResponseEntity.ok(adminservice.deleteLocation(location_id));
    }



}
