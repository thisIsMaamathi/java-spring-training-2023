package cdw.springtraining.moviebooking.services;

import cdw.springtraining.moviebooking.entity.Location;
import cdw.springtraining.moviebooking.repository.LocationRepository;
import cdw.springtraining.moviebooking.requestbody.AddLocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    LocationRepository locationRepository;
    @Autowired
    public AdminService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(AddLocationRequest request) {
        Location location=new Location(request.getName(), request.getState(), request.isPrime());
        locationRepository.save(location);
        return location;

    }

    public String deleteLocation(int locationId) {
        locationRepository.deleteById(locationId);
        return "Deleted";
    }

    public String showHome() {

        return "Home page here";
    }


}
