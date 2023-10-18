package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository <Resident,Integer> {
    boolean existsByAadhar(Long aadhar);

    Resident findByAadhar(Long aadhar);



    boolean existsByResidenceNumber(Integer residenceId);

    List<Resident> findByIsActive(boolean b);

    Resident findByAadharAndResidenceNumber(Long aadhar, Integer residenceId);
}
