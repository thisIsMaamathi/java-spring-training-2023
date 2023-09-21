package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {


    List<Show> findByDate(LocalDate date);
}
