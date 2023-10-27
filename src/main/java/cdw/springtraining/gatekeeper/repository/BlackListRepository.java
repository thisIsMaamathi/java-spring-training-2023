package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<Blacklist,Integer> {
    boolean existsByAadhar(Long aadhar);
}
