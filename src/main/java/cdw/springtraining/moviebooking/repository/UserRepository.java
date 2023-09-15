package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<Object> findByUsername(String username);
}
