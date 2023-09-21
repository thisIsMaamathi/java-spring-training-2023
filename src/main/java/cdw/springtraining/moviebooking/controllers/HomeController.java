package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    AdminService adminService;
    @GetMapping("/home")
    public ResponseEntity<String> showHome(){
        return ResponseEntity.ok(adminService.showHome());
    }

}
