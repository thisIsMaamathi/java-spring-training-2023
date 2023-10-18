package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {

    boolean existsByJwt(String jwt);





    void deleteByJwt(String token);

    Optional<Token> findByJwt(String token);
}
