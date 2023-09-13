package cdw.domaintraining.spring.security.practice.repo;

import cdw.domaintraining.spring.security.practice.entity.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<JwtUser,Integer> {

    Optional<JwtUser> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<JwtUser> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}
