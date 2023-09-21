package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Movie;
import cdw.springtraining.moviebooking.exception.ElementNotFoundException;
import cdw.springtraining.moviebooking.repository.MovieRepository;
import cdw.springtraining.moviebooking.requestbody.MovieRequest;
import cdw.springtraining.moviebooking.responseobjects.MovieResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Service
public class MovieServices {
    MovieRepository movieRepository;

    @Autowired
    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse addMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setMovieName(request.getMovieName());
        movie.setDescription(request.getDescription());
        movieRepository.save(movie);

        return new MovieResponse(movie.getMovieName(), movie.getDescription());


    }

    public MovieResponse getMovie(String movieName) {
        Optional<Movie> movie = movieRepository.findByMovieName(movieName);
        if (movie.isPresent()) return new MovieResponse(movie.get());
        else throw new ElementNotFoundException("No such movie present");
    }


    public List<Movie> fetchAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;

    }

    public String removeMovie(int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return "Deleted Movie";
        } else throw new ElementNotFoundException("No such movie found");
    }

    public MovieResponse editMovie(int movieId, MovieRequest request) {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        MovieResponse response;

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();

            if (request.getMovieName() != null) {
                movie.setMovieName(request.getMovieName());
            }

            if (request.getDescription() != null) {
                movie.setDescription(request.getDescription());
            }
            movieRepository.save(movie);
            response = new MovieResponse(movie.getMovieName(), movie.getDescription());
        } else throw new ElementNotFoundException("Movie Not found");

        return response;
    }
}
