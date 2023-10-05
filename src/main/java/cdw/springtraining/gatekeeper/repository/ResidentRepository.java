package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Resident;
import cdw.springtraining.gatekeeper.models.CreateResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository <Resident,Integer> {
    boolean existsByAadhar(Long aadhar);

    Resident findByAadhar(Long aadhar);

}
