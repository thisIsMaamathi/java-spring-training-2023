package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    boolean existsByAadhar(Long aadhar);

    boolean existsByResidenceNumber(Integer residenceId);

    Optional<Users> findByUserName(String username);

    List<Users> findByIsActive(boolean b);

    List<Users> findByIsApproved(String approved);

    List<Users> findByUserTypeAndIsActiveAndIsApproved(String resident, boolean b, String approved);
}
