package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.CancellationRequest;
import cdw.springtraining.moviebooking.entity.Show;
import cdw.springtraining.moviebooking.entity.User;
import cdw.springtraining.moviebooking.repository.CancelRequestsRepository;
import cdw.springtraining.moviebooking.repository.ShowRepository;
import cdw.springtraining.moviebooking.repository.UserRepository;
import cdw.springtraining.moviebooking.requestbody.BookingRequest;
import cdw.springtraining.moviebooking.requestbody.CancellationRequestBody;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
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

    public BookedShowResponse bookForSomeone(BookingRequest request) {
        Optional<Show> show = showRepository.findById(request.getShowId());
        Optional<User> user = userRepository.findById(request.getUserId());

        show.get().getTicketsList().add(user.get());
        showRepository.save(show.get());
        logger.info("Booked ticket");
        BookedShowResponse response = new BookedShowResponse(show.get().getShow_id(), show.get().getSlot(), show.get().getMovie().getMovieName(), show.get().getLocation().getName(),
                user.get().getUserName());
        return response;

    }


    public List<CancellationRequest> viewCancellation() {
        return cancelRequestsRepository.findAll();

    }

    public String cancelRequest(CancellationRequestBody requestBody) {
        Optional<Show> show = showRepository.findById(requestBody.getUserId());
        Optional<User> user = userRepository.findById(requestBody.getShowId());

        user.get().getBookedShows().remove(show.get());
        show.get().getTicketsList().remove(user.get());
        showRepository.save(show.get());
        return "cancelled your booking";

    }


}
