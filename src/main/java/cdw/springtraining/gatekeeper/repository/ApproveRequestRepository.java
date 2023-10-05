package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.ApproveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApproveRequestRepository extends JpaRepository<ApproveRequest,Integer> {
}
