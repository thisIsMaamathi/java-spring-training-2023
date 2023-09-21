package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.CancellationRequest;
import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.entity.User;
import cdw.springtraining.moviebooking.exception.CapacityFullException;
import cdw.springtraining.moviebooking.exception.ElementNotFoundException;
import cdw.springtraining.moviebooking.repository.CancelRequestsRepository;
import cdw.springtraining.moviebooking.repository.ShowRepository;
import cdw.springtraining.moviebooking.repository.UserRepository;
import cdw.springtraining.moviebooking.requestbody.TicketRequest;
import cdw.springtraining.moviebooking.requestbody.CancellationRequestBody;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessUserService {
    private static final Logger logger = LogManager.getLogger(BusinessUserService.class);

    ShowRepository showRepository;
    UserRepository userRepository;
    CancelRequestsRepository cancelRequestsRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public BusinessUserService(ShowRepository showRepository, UserRepository userRepository, CancelRequestsRepository cancelRequestsRepository) {
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.cancelRequestsRepository = cancelRequestsRepository;
    }

    public BookedShowResponse bookForSomeone(TicketRequest request) throws Exception {
        Optional<Show> optionalShow = showRepository.findById(request.getShowId());
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

         Show show=optionalShow.orElseThrow(()-> new ElementNotFoundException("Show is not present"));
         User user=optionalUser.orElseThrow(()->new ElementNotFoundException("User doesnot exist"));

         if(show.getCount()>show.getTicketsList().size()) {

             show.getTicketsList().add(user);
             showRepository.save(show);
             logger.info("Booked ticket");
             logger.info("test show name", show.getLocation() + "  " + show.getMovie());
             BookedShowResponse response = new BookedShowResponse(show.getShow_id(), show.getSlot(), show.getMovie().getMovieName(), show.getLocation().getName(),
                     user.getUserName());
             return response;

         }
         else throw new CapacityFullException("Show is full");


    }


    public List<CancellationRequest> viewCancellation() {
        logger.info("Returning all cancellation requests");
        return cancelRequestsRepository.findAll();

    }

    public String cancelRequest(CancellationRequestBody requestBody) {
        Optional<Show> optionalShow = showRepository.findById(requestBody.getUserId());
        Optional<User> optionalUser = userRepository.findById(requestBody.getShowId());

        Show show=optionalShow.orElseThrow(()-> new ElementNotFoundException("Show not found"));
        User user=optionalUser.orElseThrow(()-> new ElementNotFoundException("User not found"));

        user.getBookedShows().remove(show);
        show.getTicketsList().remove(user);
        showRepository.save(show);
        logger.info("cancelling the ticket");
        return "cancelled your booking";

    }
    public String cancelShow(int showId) {
        Optional<Show> optionalShow=showRepository.findById(showId);

        Show show=optionalShow.orElseThrow(()-> new ElementNotFoundException("Show not found"));
        showRepository.delete(show);
        logger.info("Deleted the show");
       return "deleted you show";


    }


    public List<ShowResponse> viewBookingsByAUser(int userId) {
        Optional<User> optionaluser = userRepository.findById(userId);
        User user=optionaluser.orElseThrow(()->new ElementNotFoundException("User not found"));

        return user.getBookedShows().stream().map(show -> new ShowResponse(show.getSlot(),show.getCount(),show.getDate(),show.getMovie().getMovieName(),show.getLocation().getName())).toList();

    }


}
