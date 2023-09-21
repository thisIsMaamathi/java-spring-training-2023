package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.exception.CannotBookPrimeLocationException;
import cdw.springtraining.moviebooking.exception.HasNotBookedException;
import cdw.springtraining.moviebooking.requestbody.CartRequest;
import cdw.springtraining.moviebooking.requestbody.TicketRequest;
import cdw.springtraining.moviebooking.requestbody.UserRegistrationRequest;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import cdw.springtraining.moviebooking.responseobjects.CartResponse;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import cdw.springtraining.moviebooking.services.EndUserService;
import cdw.springtraining.moviebooking.services.MovieServices;
import cdw.springtraining.moviebooking.services.ShowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BookedShowResponse> bookTicket(@RequestBody TicketRequest request)throws Exception {
        return ResponseEntity.ok(endUserService.bookTicket(request));

    }

    @GetMapping("/show")
    public ResponseEntity<ShowResponse> checkBooking(@RequestBody TicketRequest request) throws Exception {
        return ResponseEntity.ok(endUserService.getABooking(request));
    }

    @PostMapping("/cancel/{userId}/{showId}")
    public ResponseEntity<String> requestCancellation(@PathVariable int showId,@PathVariable int userId){
        return ResponseEntity.ok(endUserService.requestCancellation(showId,userId));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) throws Exception{
        return ResponseEntity.ok(endUserService.registerUser(request));
    }

    @PostMapping("/addToCart")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) throws Exception {
        return ResponseEntity.ok(endUserService.addTicketToCart(request));
    }






}
