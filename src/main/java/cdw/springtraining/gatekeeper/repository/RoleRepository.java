package cdw.springtraining.gatekeeper.repository;

import cdw.springtraining.gatekeeper.entites.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByRoleName(String userType);
}
