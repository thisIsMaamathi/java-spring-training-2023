package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    Location findByName(String location);
}
