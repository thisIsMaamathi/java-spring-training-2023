package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    Optional<Location> findByName(String location);
}
