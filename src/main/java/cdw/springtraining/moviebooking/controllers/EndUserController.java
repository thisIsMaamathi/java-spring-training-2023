package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.requestbody.BookingRequest;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import cdw.springtraining.moviebooking.services.EndUserService;
import cdw.springtraining.moviebooking.services.MovieServices;
import cdw.springtraining.moviebooking.services.ShowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class EndUserController {
    EndUserService endUserService;
    MovieServices movieServices;
    ShowServices showServices;
    @Autowired
    public EndUserController(MovieServices movieServices, ShowServices showServices,EndUserService endUserService) {
        this.movieServices = movieServices;
        this.showServices = showServices;
        this.endUserService=endUserService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookedShowResponse> bookTicket(@RequestBody BookingRequest request){
        return ResponseEntity.ok(endUserService.bookTicket(request));

    }




}
