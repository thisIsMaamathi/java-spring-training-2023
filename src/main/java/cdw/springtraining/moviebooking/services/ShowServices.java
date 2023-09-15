package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Location;
import cdw.springtraining.moviebooking.entity.Movie;
import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.repository.LocationRepository;
import cdw.springtraining.moviebooking.repository.MovieRepository;
import cdw.springtraining.moviebooking.repository.ShowRepository;
import cdw.springtraining.moviebooking.requestbody.AddShowRequest;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServices {

    ShowRepository showRepository;
    LocationRepository locationRepository;
    MovieRepository movieRepository;

    @Autowired
    public ShowServices(ShowRepository showRepository, LocationRepository locationRepository, MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.locationRepository = locationRepository;
        this.movieRepository = movieRepository;
    }

    public ShowResponse addShow(AddShowRequest request) {

        Show show = new Show();
        show.setCount(request.getCount());
        show.setSlot(request.getSlot());

        Movie movie = new Movie();
        movie = movieRepository.findByMovieName(request.getMovie());
        show.setMovie(movie);

        Location location = locationRepository.findByName(request.getLocation());
        show.setLocation(location);
        showRepository.save(show);
        return new ShowResponse(request.getSlot(), request.getCount(), request.getMovie(), request.getLocation());


    }

    public List<Show> getShows() {
        List<Show> show= showRepository.findAll();
        return show;
    }

    public String deleteShow(int showId) {
        showRepository.deleteById(showId);
        return "deleted";
    }
}
