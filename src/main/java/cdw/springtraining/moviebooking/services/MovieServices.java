package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Movie;
import cdw.springtraining.moviebooking.repository.MovieRepository;
import cdw.springtraining.moviebooking.requestbody.AddMovieRequest;
import cdw.springtraining.moviebooking.responseobjects.MovieResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class MovieServices {
    MovieRepository movieRepository;

    @Autowired
    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse addMovie(AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setMovieName(request.getMovieName());
        movie.setDescription(request.getDescription());
        movieRepository.save(movie);

        return new MovieResponse(movie.getMovieName(), movie.getDescription());


    }

    public MovieResponse getMovie(String movieName) {
        return new MovieResponse(movieRepository.findByMovieName(movieName));
    }


    public List<Movie> fetchAllMovies() {
        List<Movie> movies=movieRepository.findAll();
        return movies;

    }

    public String removeMovie(int movieId) {
        movieRepository.deleteById(movieId);
        return "Deleted Movie";
    }

}
