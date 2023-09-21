package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.requestbody.ShowRequest;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import cdw.springtraining.moviebooking.responseobjects.UserResponse;
import cdw.springtraining.moviebooking.services.ShowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    ShowServices showServices;
    @Autowired
    public ShowController(ShowServices showServices) {
        this.showServices = showServices;
    }

    @PostMapping("/")
    public ResponseEntity<ShowResponse> addAShow(@RequestBody ShowRequest request)throws Exception{
        return ResponseEntity.ok(showServices.addShow(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<ShowResponse>> getAllShows(){
        return ResponseEntity.ok(showServices.getShows());
    }
    @GetMapping("/{show_id}")
    public ResponseEntity<List<UserResponse>> getBookings(@PathVariable int show_id){
        return ResponseEntity.ok(showServices.getBookingsForAShow(show_id));
    }

    @DeleteMapping("/{show_id}")
    public ResponseEntity<String> deleteShows(@PathVariable int show_id){
        return ResponseEntity.ok(showServices.deleteShow(show_id));
    }

   @PutMapping("/{show_id}")
    public ResponseEntity<ShowResponse> editShow(@PathVariable int show_id,@RequestBody ShowRequest request){
        return ResponseEntity.ok(showServices.updateShow(show_id,request));

   }



}
