package cdw.springtraining.moviebooking.controllers;

import cdw.springtraining.moviebooking.entity.Movie;
import cdw.springtraining.moviebooking.requestbody.AddMovieRequest;
import cdw.springtraining.moviebooking.responseobjects.MovieResponse;
import cdw.springtraining.moviebooking.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieServices movieServices;
    @Autowired
    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }

    //crud operations
    @PostMapping("/")
    public ResponseEntity<MovieResponse> addAMovie(@RequestBody AddMovieRequest request){
        return ResponseEntity.ok(movieServices.addMovie(request));
    }

    @PostMapping("/name")
    public ResponseEntity<MovieResponse> getaAMovie(@RequestParam String movieName){
        return ResponseEntity.ok(movieServices.getMovie(movieName));
    }

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAllMovies (){
        return ResponseEntity.ok(movieServices.fetchAllMovies());
    }

    @DeleteMapping("/{movie_id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int movie_id){
        return ResponseEntity.ok(movieServices.removeMovie(movie_id));
    }




}
