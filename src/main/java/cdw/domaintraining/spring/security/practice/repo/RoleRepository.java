package cdw.domaintraining.spring.security.practice.repo;

import cdw.domaintraining.spring.security.practice.entity.JwtRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<JwtRole, Long> {
    Optional<JwtRole> findByName(String name);
}

