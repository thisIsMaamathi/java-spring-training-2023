package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.requestbody.AddShowRequest;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
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
    public ResponseEntity<ShowResponse> addAShow(@RequestBody AddShowRequest request){
        return ResponseEntity.ok(showServices.addShow(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<Show>> getAllShows(){
        return ResponseEntity.ok(showServices.getShows());
    }

    @DeleteMapping("/{show_id}")
    public ResponseEntity<String> deleteShows(@PathVariable int show_id){
        return ResponseEntity.ok(showServices.deleteShow(show_id));
    }



}
