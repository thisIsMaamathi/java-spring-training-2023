package cdw.springtraining.meetingscheduler.Repository;

import cdw.springtraining.meetingscheduler.Entities.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Teams,Integer> {
    boolean findEmployeeByTeamId(int teamId);
}
