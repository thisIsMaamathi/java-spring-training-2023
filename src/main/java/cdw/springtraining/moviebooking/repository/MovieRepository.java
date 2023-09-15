package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findByMovieName(String movieName);
}
