package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.CancellationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelRequestsRepository extends JpaRepository<CancellationRequest,Integer> {
}
