package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.GateKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateKeeperRepository extends JpaRepository<GateKeeper,Integer> {
    boolean existsByAadhar(Long aadhar);

    GateKeeper findByAadhar(Long aadhar);

}
