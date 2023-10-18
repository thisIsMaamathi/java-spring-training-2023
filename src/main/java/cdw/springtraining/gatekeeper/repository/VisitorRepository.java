package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface VisitorRepository extends JpaRepository<Visitors,Integer> {
    List<Visitors> findByDate(LocalDate date);

    Visitors findByPass(String visitorPass);

    boolean existsByAadharAndDateAndHouseNumber(Long aadhar, LocalDate date, int residenceNumber);

    Visitors findByAadhar(Long aadhar);

}
