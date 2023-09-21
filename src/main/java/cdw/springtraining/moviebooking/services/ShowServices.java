package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Location;
import cdw.springtraining.moviebooking.entity.Movie;
import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.exception.CapacityFullException;
import cdw.springtraining.moviebooking.exception.CustomException;
import cdw.springtraining.moviebooking.exception.ElementNotFoundException;
import cdw.springtraining.moviebooking.exception.ShowAlreadyExistsException;
import cdw.springtraining.moviebooking.repository.LocationRepository;
import cdw.springtraining.moviebooking.repository.MovieRepository;
import cdw.springtraining.moviebooking.repository.ShowRepository;
import cdw.springtraining.moviebooking.requestbody.ShowRequest;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import cdw.springtraining.moviebooking.responseobjects.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServices {
    private static final Logger logger = LogManager.getLogger(ShowServices.class);
    ShowRepository showRepository;
    LocationRepository locationRepository;
    MovieRepository movieRepository;

    @Autowired
    public ShowServices(ShowRepository showRepository, LocationRepository locationRepository, MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.locationRepository = locationRepository;
        this.movieRepository = movieRepository;
    }

    public boolean isShowEngaged(ShowRequest request) {
        boolean showEngaged = false;
        List<Show> bookedshows = showRepository.findByDate(request.getDate());
        if (!bookedshows.isEmpty()) {

            showEngaged = bookedshows.stream().anyMatch(show ->
                    (show.getSlot() == request.getSlot())
                            &&
                            (show.getLocation().getName().equals(request.getLocation()))

            );
        }
        return showEngaged;
    }

    public ShowResponse addShow(ShowRequest request) throws Exception {
        //if show is existing in previous booked location
        if (isShowEngaged(request)) {
            throw new ShowAlreadyExistsException("Show already exists");
        }


        Show show = new Show();
        show.setCount(request.getCount());
        show.setSlot(request.getSlot());
        show.setDate(request.getDate());

        Optional<Movie> optionalMovie = movieRepository.findByMovieName(request.getMovie());
        Optional<Location> optionalLocation = locationRepository.findByName(request.getLocation());

        Movie movie = optionalMovie.orElseThrow(() -> new ElementNotFoundException("Movie doesnot exist"));
        Location location = optionalLocation.orElseThrow(() -> new ElementNotFoundException("Location doesnot exist"));

        show.setLocation(location);
        location.getRegionalShowsList().add(show);
        show.setMovie(movie);
        movie.getMovieShowsList().add(show);
        showRepository.save(show);

        logger.info("Booked Show");
        return new ShowResponse(request.getSlot(), request.getCount(),show.getDate(),request.getMovie(), request.getLocation());

    }


    public List<ShowResponse> getShows() {
        List<Show> shows = showRepository.findAll();
        logger.info("Returning all shows");
        List<ShowResponse> showResponseList = shows.stream().map(
                show -> new ShowResponse(show.getSlot(), show.getCount(),show.getDate(), show.getMovie().getMovieName(), show.getLocation().getName())
        ).toList();

        return showResponseList;
    }

    public String deleteShow(int showId) {
        Optional<Show> show=showRepository.findById(showId);
        if(show.isPresent()) {
            showRepository.deleteById(showId);
            logger.info("Deleted the show");
            return "Deleted";
        } else throw new ElementNotFoundException("No such show found");
    }

    public ShowResponse updateShow(int showId, ShowRequest request) throws Exception{

        Optional<Show> optionalShow = showRepository.findById(showId);
        ShowResponse showResponse;
        if (optionalShow.isPresent()) {
            Show show = optionalShow.get();
            if (request.getCount() != null)
                if (request.getCount() >= show.getCount()) {
                    show.setCount(request.getCount());
                    showRepository.save(show);

                }else throw new CustomException("Capacity cannot be reduced");

            if (request.getSlot() != null)
                if (!isShowEngaged(request)) {
                    show.setSlot(request.getSlot());

                }else throw  new ShowAlreadyExistsException("A show already exists in that slot");

            if (request.getDate() != null)
                if (!isShowEngaged(request)) {
                    show.setDate(request.getDate());

                }else throw  new ShowAlreadyExistsException("A show already exists in that slot");


            if (request.getLocation() != null) {
                Optional<Location> locationOptional = locationRepository.findByName(request.getLocation());
                Location location = locationOptional.orElseThrow(() -> new ElementNotFoundException("Requested Location not found for change"));

               if(!isShowEngaged(request)) {
                   show.setLocation(location);
                   location.getRegionalShowsList().add(show);
               } else throw  new ShowAlreadyExistsException("A show already exists in that slot");

            }

            if (request.getMovie() != null) {
                Optional<Movie> optionalMovie = movieRepository.findByMovieName(request.getMovie());
                Movie movie = optionalMovie.orElseThrow(() -> new ElementNotFoundException("Requested Location not found for change"));
                show.setMovie(movie);
                movie.getMovieShowsList().add(show);


            }

            showRepository.save(show);
            showResponse = new ShowResponse(show.getSlot(), show.getCount(),show.getDate(), show.getMovie().getMovieName(), show.getLocation().getName());
        } else throw new ElementNotFoundException("Show doesnot exists");


        return showResponse;
    }

    public List<UserResponse> getBookingsForAShow(int showId) {
     Optional<Show> optionalShow=showRepository.findById(showId);
     Show show=optionalShow.orElseThrow(()->new ElementNotFoundException("Show not found"));

        logger.info("Returning all booked users");

        return show.getTicketsList().stream().map(
                user -> new UserResponse(user.getUserId() ,user.getUserName(),user.getGender())).toList();
    }
}
