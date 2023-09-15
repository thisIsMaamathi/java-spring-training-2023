package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.entity.User;
import cdw.springtraining.moviebooking.repository.LocationRepository;
import cdw.springtraining.moviebooking.repository.MovieRepository;
import cdw.springtraining.moviebooking.repository.ShowRepository;
import cdw.springtraining.moviebooking.repository.UserRepository;
import cdw.springtraining.moviebooking.requestbody.BookingRequest;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EndUserService {
    private static final Logger logger = LogManager.getLogger(EndUserService.class);
    UserRepository userRepository;
    ShowRepository showRepository;
    MovieRepository movieRepository;
    LocationRepository locationRepository;
    @Autowired
    public EndUserService(UserRepository userRepository, ShowRepository showRepository, MovieRepository movieRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.locationRepository = locationRepository;
    }

    public BookedShowResponse bookTicket(BookingRequest request) {
        Optional<Show> show = showRepository.findById(request.getShowId());
        Optional<User> user = userRepository.findById(request.getUserId());

        show.get().getTicketsList().add(user.get());
        showRepository.save(show.get());
        logger.info("Booked ticket");
        BookedShowResponse response = new BookedShowResponse(show.get().getShow_id(), show.get().getSlot(), show.get().getMovie().getMovieName(), show.get().getLocation().getName(),
                user.get().getUserName());
        return response;
    }
}
