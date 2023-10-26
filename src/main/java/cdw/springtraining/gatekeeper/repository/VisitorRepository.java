package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitors,Integer> {
    List<Visitors> findByDate(LocalDate date);

    Visitors findByAadharAndDate(Long aadhar, LocalDate date);

    List<Visitors> findAllByAadharAndDate(Long aadhar, LocalDate date);

    Visitors findByPass(String visitorPass);

    boolean existsByAadharAndDateAndResidenceId(Long aadhar, LocalDate date, Integer residentId);


    Optional<Visitors> findByAadharAndDateAndResidenceId(Long aadhar, LocalDate date, Integer residentId);
}
