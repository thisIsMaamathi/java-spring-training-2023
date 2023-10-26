package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    boolean existsByJwt(String token);

    void deleteByJwt(String target);
}
