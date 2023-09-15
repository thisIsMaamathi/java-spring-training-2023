package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
