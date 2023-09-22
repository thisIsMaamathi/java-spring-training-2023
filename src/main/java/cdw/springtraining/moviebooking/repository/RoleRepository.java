package cdw.springtraining.moviebooking.repository;

import cdw.springtraining.moviebooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleId(int i);
}
