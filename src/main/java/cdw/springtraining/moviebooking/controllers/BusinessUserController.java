package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.entity.CancellationRequest;
import cdw.springtraining.moviebooking.repository.*;
import cdw.springtraining.moviebooking.requestbody.TicketRequest;
import cdw.springtraining.moviebooking.requestbody.CancellationRequestBody;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import cdw.springtraining.moviebooking.services.BusinessUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bu")
public class BusinessUserController {
    private static final Logger logger = LogManager.getLogger(BusinessUserController.class);
    ShowRepository showRepository;
    MovieRepository movieRepository;
    UserRepository userRepository;
    LocationRepository locationRepository;
    BusinessUserService businessUserService;

    @Autowired
    public BusinessUserController(ShowRepository showRepository, MovieRepository movieRepository, UserRepository userRepository, LocationRepository locationRepository,BusinessUserService businessUserService) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.businessUserService=businessUserService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookedShowResponse> bookShowForSomeOne(@RequestBody TicketRequest request)throws Exception {
      return ResponseEntity.ok(businessUserService.bookForSomeone(request));
    }

    @GetMapping("/cancel-requests")
    public ResponseEntity<List<CancellationRequest>> showCancelRequests(){
        return ResponseEntity.ok(businessUserService.viewCancellation());
    }

    @PostMapping("/cancel-ticket")
    public ResponseEntity<String> cancelRequests(@RequestBody CancellationRequestBody request){

        return ResponseEntity.ok(businessUserService.cancelRequest(request));
    }
    @PostMapping("/cancel-show/{showId}")
    public ResponseEntity<String> cancelShows(@PathVariable int showId){

        return ResponseEntity.ok(businessUserService.cancelShow(showId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ShowResponse>> viewUserBookings(@PathVariable int userId){
        return ResponseEntity.ok(businessUserService.viewBookingsByAUser(userId));
    }




}
