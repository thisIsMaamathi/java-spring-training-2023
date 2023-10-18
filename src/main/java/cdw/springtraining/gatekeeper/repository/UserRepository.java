package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);




    boolean existsByUserNameAndAadhar( String userName, Long Aadhar);
}
