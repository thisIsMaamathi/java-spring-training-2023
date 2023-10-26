package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(String userType);
}
