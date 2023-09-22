package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.*;
import cdw.springtraining.moviebooking.exception.*;
import cdw.springtraining.moviebooking.repository.*;
import cdw.springtraining.moviebooking.requestbody.CartRequest;
import cdw.springtraining.moviebooking.requestbody.TicketRequest;
import cdw.springtraining.moviebooking.requestbody.UserRegistrationRequest;
import cdw.springtraining.moviebooking.responseobjects.BookedShowResponse;
import cdw.springtraining.moviebooking.responseobjects.CartResponse;
import cdw.springtraining.moviebooking.responseobjects.ShowResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EndUserService {
    private static final Logger logger = LogManager.getLogger(EndUserService.class);
    UserRepository userRepository;
    ShowRepository showRepository;
    MovieRepository movieRepository;
    LocationRepository locationRepository;

    RoleRepository roleRepository;
    CancelRequestsRepository cancelRequestsRepository;


    @Autowired
    public EndUserService(UserRepository userRepository, ShowRepository showRepository, MovieRepository movieRepository, LocationRepository locationRepository, RoleRepository roleRepository, CancelRequestsRepository cancelRequestsRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.locationRepository = locationRepository;
        this.roleRepository = roleRepository;
        this.cancelRequestsRepository = cancelRequestsRepository;

    }

    public BookedShowResponse bookTicket(TicketRequest request) throws Exception {
        Optional<Show> optionalShow = showRepository.findById(request.getShowId());
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        User user = optionalUser.orElseThrow(() -> new ElementNotFoundException("User not present"));
        Show show = optionalShow.orElseThrow(() -> new ElementNotFoundException("Show not present"));


        BookedShowResponse response;
        if (show.getLocation().isPrime()) throw new CannotBookPrimeLocationException("Cannot Book a primeLocation");
        else {logger.info("tickets:"+show.getTicketsList().size());
            if (show.getCount() > show.getTicketsList().size()) {
                show.getTicketsList().add(user);
                user.getBookedShows().add(show);
                showRepository.save(show);
                logger.info("Booked ticket");
                response = new BookedShowResponse(show.getShow_id(), show.getSlot(), show.getMovie().getMovieName(), show.getLocation().getName(),
                        user.getUserName());
            } else throw new CapacityFullException("capacity full");
        }
        return response;
    }

    public ShowResponse getABooking(TicketRequest request) throws Exception {
        Optional<Show> optionalShow = showRepository.findById(request.getShowId());
        Optional<User> user = userRepository.findById(request.getUserId());


        Show show = optionalShow.orElseThrow(() -> new ElementNotFoundException("Show not present"));
        if (show.getTicketsList().contains(user.get())) {
            return new ShowResponse(show.getSlot(), show.getCount(), show.getDate(), show.getMovie().getMovieName(), show.getLocation().getName());
        } else throw new HasNotBookedException("The user has not booked for this show");
    }

    public String requestCancellation(int showId, int userId) {
        CancellationRequest request = new CancellationRequest(showId, userId);
        cancelRequestsRepository.save(request);
        return "Request Uploaded";

    }

    public String registerUser(UserRegistrationRequest request) throws AccessDeniedException {
        if (calculateAge(request.getDob()) > 18) {
            User user = new User(request.getUserName(), request.getDob(), request.getGender(), request.getMailId(), request.getPassword());
            Role role = roleRepository.findByRoleId(3);
            user.getRoleList().add(role);
            role.getUsersList().add(user);
            userRepository.save(user);
            return "Registered the user";
        } else {
            logger.info("Age should be greater than 18");
            throw new AccessDeniedException("Age should be greater than 18");
        }
    }

    private int calculateAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        Period age = Period.between(dob, now);
        return age.getYears();

    }

    public CartResponse addTicketToCart(CartRequest request)throws Exception {
        Optional<Show> optionalShow = showRepository.findById(request.getShowId());
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        CartResponse cartResponse;

        User user = optionalUser.orElseThrow(() -> new ElementNotFoundException("User not present"));
        Show show = optionalShow.orElseThrow(() -> new ElementNotFoundException("Show not present"));

        int ticketsAvailable = show.getCount()-show.getTicketsList().size();
        if (ticketsAvailable>0) {
//

            ticketsAvailable--;
            cartResponse=new CartResponse(user.getUserId(), user.getUserName(),ticketsAvailable,show.getShow_id());
        }
        else throw new CapacityFullException("Capacity full");
        return cartResponse;

    }
}
